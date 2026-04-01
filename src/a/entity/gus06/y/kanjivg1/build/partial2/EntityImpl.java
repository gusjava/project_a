package a.entity.gus06.y.kanjivg1.build.partial2;

import a.framework.*;
import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;

public class EntityImpl implements Entity, T {

    public String creationDate() { return "20250810"; }

    public EntityImpl() throws Exception { }

    // Nombre d'�chantillons utilis�s pour approximer la longueur d'une courbe
    private static final int SAMPLES_PER_SEGMENT = 50;

    // Repr�sentation interne d'un segment de chemin (moveto non stock� comme segment)
    private static class Seg {
        enum Type { LINE, QUAD, CUBIC, CLOSE }
        Type type;
        // points : p0 = start (toujours pr�sent), ensuite ctrl(s), puis end
        Point2D.Float p0, c1, c2, p1;
        // longueur approximative et table des longueurs cumul�es pour inversion param<->longueur
        float length;
        float[] cum; // taille SAMPLES_PER_SEGMENT+1, cum[0]=0, cum[last]=length

        // constructeur pour line
        Seg(Point2D.Float p0, Point2D.Float p1) {
            this.type = Type.LINE;
            this.p0 = p0; this.p1 = p1;
            buildLengthTableLine();
        }

        // constructeur pour quad
        Seg(Point2D.Float p0, Point2D.Float ctrl, Point2D.Float p1) {
            this.type = Type.QUAD;
            this.p0 = p0; this.c1 = ctrl; this.p1 = p1;
            buildLengthTableBezierQuadratic();
        }

        // constructeur pour cubic
        Seg(Point2D.Float p0, Point2D.Float ctrl1, Point2D.Float ctrl2, Point2D.Float p1) {
            this.type = Type.CUBIC;
            this.p0 = p0; this.c1 = ctrl1; this.c2 = ctrl2; this.p1 = p1;
            buildLengthTableBezierCubic();
        }

        // ligne : longueur triviale
        private void buildLengthTableLine() {
            float dx = (float)(p1.x - p0.x);
            float dy = (float)(p1.y - p0.y);
            this.length = (float)Math.hypot(dx, dy);
            this.cum = new float[2];
            this.cum[0] = 0f;
            this.cum[1] = this.length;
        }

        // �chantillonnage d'une quad B�zier
        private void buildLengthTableBezierQuadratic() {
            int n = SAMPLES_PER_SEGMENT;
            cum = new float[n + 1];
            cum[0] = 0f;
            float prevX = p0.x, prevY = p0.y;
            float acc = 0f;
            for (int i = 1; i <= n; i++) {
                float t = i / (float) n;
                Point2D.Float pt = pointOnQuad(p0, c1, p1, t);
                float dx = pt.x - prevX;
                float dy = pt.y - prevY;
                acc += (float)Math.hypot(dx, dy);
                cum[i] = acc;
                prevX = pt.x; prevY = pt.y;
            }
            this.length = acc;
        }

        // �chantillonnage d'une cubic B�zier
        private void buildLengthTableBezierCubic() {
            int n = SAMPLES_PER_SEGMENT;
            cum = new float[n + 1];
            cum[0] = 0f;
            float prevX = p0.x, prevY = p0.y;
            float acc = 0f;
            for (int i = 1; i <= n; i++) {
                float t = i / (float) n;
                Point2D.Float pt = pointOnCubic(p0, c1, c2, p1, t);
                float dx = pt.x - prevX;
                float dy = pt.y - prevY;
                acc += (float)Math.hypot(dx, dy);
                cum[i] = acc;
                prevX = pt.x; prevY = pt.y;
            }
            this.length = acc;
        }

        // retourne t (approx) correspondant � la longueur l (0..length) sur ce segment
        // renvoie 0..1
        float findTForLength(float l) {
            if (l <= 0f) return 0f;
            if (l >= length) return 1f;
            if (type == Type.LINE) {
                return l / length;
            }
            // recherche lin�aire/interpol�e dans cum
            int n = cum.length - 1;
            // bsearch
            int lo = 0, hi = n;
            while (lo + 1 < hi) {
                int mid = (lo + hi) >>> 1;
                if (cum[mid] < l) lo = mid;
                else hi = mid;
            }
            float left = cum[lo];
            float right = cum[hi];
            float frac = (right - left) == 0f ? 0f : (l - left) / (right - left);
            float tLeft = lo / (float) n;
            float tRight = hi / (float) n;
            return tLeft + (tRight - tLeft) * frac;
        }

        // utilitaires statiques pour points sur B�zier
        static Point2D.Float pointOnQuad(Point2D.Float a, Point2D.Float b, Point2D.Float c, float t) {
            float mt = 1 - t;
            float x = mt*mt*a.x + 2*mt*t*b.x + t*t*c.x;
            float y = mt*mt*a.y + 2*mt*t*b.y + t*t*c.y;
            return new Point2D.Float(x, y);
        }

        static Point2D.Float pointOnCubic(Point2D.Float a, Point2D.Float b, Point2D.Float c, Point2D.Float d, float t) {
            float mt = 1 - t;
            float mt2 = mt*mt;
            float t2 = t*t;
            float x = mt2*mt*a.x + 3*mt2*t*b.x + 3*mt*t2*c.x + t2*t*d.x;
            float y = mt2*mt*a.y + 3*mt2*t*b.y + 3*mt*t2*c.y + t2*t*d.y;
            return new Point2D.Float(x, y);
        }

        // subdivision (De Casteljau) : on retourne le sous-bezier entre t0 et t1
        // pour quad : renvoie [newP0, newCtrl, newP1]
        static Point2D.Float[] subdivideQuad(Point2D.Float P0, Point2D.Float P1, Point2D.Float P2, float t0, float t1) {
            // Split at t0 -> right part, param remapped; then split right part at t' to get left part (t0..t1)
            // First split at t0
            float t = t0;
            Point2D.Float A = lerp(P0, P1, t);
            Point2D.Float B = lerp(P1, P2, t);
            Point2D.Float C = lerp(A, B, t); // point at t0

            // Right part control points: R0=C, R1 = lerp(B, P2, t) ??? Wait: de Casteljau right part control:
            // Actually splitting gives left: (P0, A, C) and right: (C, B, P2)
            Point2D.Float R0 = C, R1 = B, R2 = P2;

            // Now split right part at t' = (t1-t0)/(1-t0)
            float tPrime = (t1 - t0) / (1 - t0);
            // right part points are [C, B, P2]
            Point2D.Float A2 = lerp(R0, R1, tPrime);
            Point2D.Float B2 = lerp(R1, R2, tPrime);
            Point2D.Float C2 = lerp(A2, B2, tPrime); // point at t1

            // resulting sub-curve (from t0..t1) has control points [C, A2, C2]
            return new Point2D.Float[] { R0, A2, C2 };
        }

        // subdivide cubic: renvoie [newP0, newCtrl1, newCtrl2, newP1]
        static Point2D.Float[] subdivideCubic(Point2D.Float P0, Point2D.Float P1, Point2D.Float P2, Point2D.Float P3, float t0, float t1) {
            // Split at t0 -> take right part, then split right part at t' to get t0..t1
            // First split at t0 to get right part (R0..R3)
            // De Casteljau
            // Level1
            Point2D.Float L01 = lerp(P0, P1, t0);
            Point2D.Float L12 = lerp(P1, P2, t0);
            Point2D.Float L23 = lerp(P2, P3, t0);
            // Level2
            Point2D.Float L012 = lerp(L01, L12, t0);
            Point2D.Float L123 = lerp(L12, L23, t0);
            // Level3 - point at t0
            Point2D.Float L0123 = lerp(L012, L123, t0);

            // Right part control points are [L0123, L123, L23, P3] ??? Careful: correct right part after split:
            // After split at t0, left is [P0, L01, L012, L0123], right is [L0123, L123, L23, P3]
            Point2D.Float R0 = L0123;
            Point2D.Float R1 = L123;
            Point2D.Float R2 = L23;
            Point2D.Float R3 = P3;

            // Now split right part at t' = (t1 - t0)/(1 - t0)
            float tPrime = (t1 - t0) / (1 - t0);
            // apply De Casteljau on R0,R1,R2,R3 with tPrime
            Point2D.Float M01 = lerp(R0, R1, tPrime);
            Point2D.Float M12 = lerp(R1, R2, tPrime);
            Point2D.Float M23 = lerp(R2, R3, tPrime);
            Point2D.Float M012 = lerp(M01, M12, tPrime);
            Point2D.Float M123 = lerp(M12, M23, tPrime);
            Point2D.Float M0123 = lerp(M012, M123, tPrime);

            // left-of-right (which corresponds to t0..t1) control points are [R0, M01, M012, M0123]
            return new Point2D.Float[] { R0, M01, M012, M0123 };
        }

        static Point2D.Float lerp(Point2D.Float a, Point2D.Float b, float t) {
            return new Point2D.Float(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t);
        }
    }

    // ---------------------------------------------------
    // M�thode principale : construit le Path2D.Float partiel en respectant les courbes
    // ---------------------------------------------------
    public Object t(Object obj) throws Exception {
        Object[] o = (Object[]) obj;
        if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

        Shape stroke = (Shape) o[0];
        float progress = ((Float) o[1]).floatValue();
        if (progress < 0f) progress = 0f;
        if (progress > 1f) progress = 1f;

        // IMPORTANT : on r�cup�re les segments sans aplatissement
        PathIterator it = stroke.getPathIterator(null);

        List<Seg> segs = new ArrayList<>();
        float[] coords = new float[6];
        Point2D.Float current = null;
        Point2D.Float startOfSubpath = null; // pour CLOSE

        while (!it.isDone()) {
            int type = it.currentSegment(coords);
            switch (type) {
                case PathIterator.SEG_MOVETO:
                    current = new Point2D.Float(coords[0], coords[1]);
                    startOfSubpath = current;
                    break;
                case PathIterator.SEG_LINETO: {
                    Point2D.Float next = new Point2D.Float(coords[0], coords[1]);
                    segs.add(new Seg(current, next));
                    current = next;
                    break;
                }
                case PathIterator.SEG_QUADTO: {
                    Point2D.Float ctrl = new Point2D.Float(coords[0], coords[1]);
                    Point2D.Float next = new Point2D.Float(coords[2], coords[3]);
                    segs.add(new Seg(current, ctrl, next));
                    current = next;
                    break;
                }
                case PathIterator.SEG_CUBICTO: {
                    Point2D.Float ctrl1 = new Point2D.Float(coords[0], coords[1]);
                    Point2D.Float ctrl2 = new Point2D.Float(coords[2], coords[3]);
                    Point2D.Float next = new Point2D.Float(coords[4], coords[5]);
                    segs.add(new Seg(current, ctrl1, ctrl2, next));
                    current = next;
                    break;
                }
                case PathIterator.SEG_CLOSE: {
                    if (current != null && startOfSubpath != null && (!current.equals(startOfSubpath))) {
                        segs.add(new Seg(current, new Point2D.Float(startOfSubpath.x, startOfSubpath.y)));
                        current = startOfSubpath;
                    }
                    startOfSubpath = null;
                    break;
                }
            }
            it.next();
        }

        // total length
        float totalLength = 0f;
        for (Seg s : segs) totalLength += s.length;

        float target = progress * totalLength;

        Path2D.Float partial = new Path2D.Float();
        boolean started = false;
        float acc = 0f;

        for (Seg s : segs) {
            if (acc >= target) break;
            float segLen = s.length;
            if (acc + segLen <= target + 1e-6f) {
                // ajouter le segment entier
                if (!started) {
                    // moveTo au d�but du segment
                    partial.moveTo(s.p0.x, s.p0.y);
                    started = true;
                }
                appendWholeSegmentToPath(partial, s);
                acc += segLen;
            } else {
                // on doit ajouter une portion de ce segment
                float remain = target - acc; // >0 && < segLen
                float tEnd = s.findTForLength(remain); // 0..1
                if (!started) {
                    // si on commence � l'int�rieur d'un segment (rare), on doit moveTo au point � t=0
                    partial.moveTo(s.p0.x, s.p0.y);
                    started = true;
                }
                appendPartialSegmentToPath(partial, s, 0f, tEnd);
                acc = target;
                break;
            }
        }

        return partial;
    }

    // Ajoute un segment entier (line/quad/cubic) dans partial
    private static void appendWholeSegmentToPath(Path2D.Float p, Seg s) {
        switch (s.type) {
            case LINE:
                p.lineTo(s.p1.x, s.p1.y);
                break;
            case QUAD:
                p.quadTo(s.c1.x, s.c1.y, s.p1.x, s.p1.y);
                break;
            case CUBIC:
                p.curveTo(s.c1.x, s.c1.y, s.c2.x, s.c2.y, s.p1.x, s.p1.y);
                break;
            default:
                break;
        }
    }

    // Ajoute la portion t in [t0,t1] du segment s au Path2D p en utilisant les primitives quadTo/curveTo/lineTo
    private static void appendPartialSegmentToPath(Path2D.Float p, Seg s, float t0, float t1) {
        if (t0 >= t1) return;
        switch (s.type) {
            case LINE: {
                // pour une ligne, t0..t1 -> simple interpolation
                float sx = s.p0.x + (s.p1.x - s.p0.x) * t0;
                float sy = s.p0.y + (s.p1.y - s.p0.y) * t0;
                float ex = s.p0.x + (s.p1.x - s.p0.x) * t1;
                float ey = s.p0.y + (s.p1.y - s.p0.y) * t1;
                p.lineTo(ex, ey);
                break;
            }
            case QUAD: {
                // subdiviser la quad entre t0 et t1 pour obtenir un quad exacte
                Point2D.Float[] sub = Seg.subdivideQuad(s.p0, s.c1, s.p1, t0, t1);
                // sub = [newP0, newCtrl, newP1]
                // si on est d�j� au newP0 dans le path, on doit quadTo(newCtrl, newP1)
                p.quadTo(sub[1].x, sub[1].y, sub[2].x, sub[2].y);
                break;
            }
            case CUBIC: {
                Point2D.Float[] sub = Seg.subdivideCubic(s.p0, s.c1, s.c2, s.p1, t0, t1);
                // sub = [newP0, newCtrl1, newCtrl2, newP1]
                p.curveTo(sub[1].x, sub[1].y, sub[2].x, sub[2].y, sub[3].x, sub[3].y);
                break;
            }
            default:
                break;
        }
    }
}
