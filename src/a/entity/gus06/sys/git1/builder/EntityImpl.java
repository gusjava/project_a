package a.entity.gus06.sys.git1.builder;

import a.framework.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.FetchCommand;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.FollowFilter;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.revwalk.RenameCallback;
import org.eclipse.jgit.diff.DiffConfig;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.RenameDetector;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200115";}


	private Service findDir;
	private Service genNum;
	private Service cmdPull;
	private Service cmdPush;


	public EntityImpl() throws Exception
	{
		findDir = Outside.service(this,"gus06.sys.git1.find.gitfolder");
		genNum = Outside.service(this,"gus06.data.generate.string.random.number8");
		cmdPull = Outside.service(this,"gus06.sys.git1.cmd.pull");
		cmdPush = Outside.service(this,"gus06.sys.git1.cmd.push");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File gitDir = (File) findDir.t(obj);
		if(gitDir==null) return null;
		
		return new Holder(gitDir);
	}
	
	
	
	private class Holder implements R, P, V, F
	{
		private File gitDir;
		private File gitRoot;
		private Git git;
		private Repository repo;
		private Config config;
		
		public Holder(File gitDir) throws Exception
		{
			this.gitDir = gitDir;
			this.gitRoot = gitDir.getParentFile();
			
			git = Git.open(gitDir);
			repo = git.getRepository();
			config = repo.getConfig();
			
			config.setBoolean("diff", null, "renames", true);
		}
		
		public void p(Object obj) throws Exception
		{
			String cmd = (String) obj;
			
			if(cmd.equals("close")) {git.close();return;}
			if(cmd.equals("pull")) {pull();return;}
			if(cmd.equals("push")) {push();return;}
			if(cmd.equals("fetch")) {fetch();return;}
			if(cmd.equals("dropAllStashes")) {dropAllStashes();return;}
			
			if(cmd.startsWith("merge:")) {merge(m(cmd));return;}
			if(cmd.startsWith("commit:")) {commit(m(cmd));return;}
			if(cmd.startsWith("checkout:")) {checkout(m(cmd));return;}
			if(cmd.startsWith("createBranch:")) {createBranch(m(cmd));return;}
			if(cmd.startsWith("createStash:")) {createStash(m(cmd));return;}
			if(cmd.startsWith("dropStash:")) {dropStash(m(cmd));return;}
			if(cmd.startsWith("applyStash:")) {applyStash(m(cmd));return;}
			if(cmd.startsWith("applyDropStash:")) {applyDropStash(m(cmd));return;}
			
			throw new Exception("Unsupported command: "+cmd);
		}
		
		public boolean f(Object obj) throws Exception
		{
			String cmd = (String) obj;
			
			if(cmd.startsWith("hasStash:")) return hasStash(m(cmd));
			if(cmd.startsWith("dropStash:")) return dropStash(m(cmd));
			if(cmd.startsWith("applyStash:")) return applyStash(m(cmd));
			if(cmd.startsWith("applyDropStash:")) return applyDropStash(m(cmd));
			
			throw new Exception("Unsupported command: "+cmd);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("pull")) {pull(obj);return;}
			if(key.equals("push")) {push(obj);return;}
			if(key.equals("commit")) {commit(obj);return;}
			
			if(key.equals("merge")) {merge((String) obj);return;}
			if(key.equals("checkout")) {checkout((String) obj);return;}
			if(key.equals("createBranch")) {createBranch((String) obj);return;}
			if(key.equals("createStash")) {createStash((String) obj);return;}
			if(key.equals("dropStash")) {dropStash((String) obj);return;}
			if(key.equals("applyStash")) {applyStash((String) obj);return;}
			if(key.equals("applyDropStash")) {applyDropStash((String) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("root")) return gitRoot;
			if(key.equals("status")) return status();
			
			if(key.equals("branch")) return branch();
			if(key.equals("branchsAll")) return branchsAll();
			if(key.equals("branchsRemote")) return branchsRemote();
			if(key.equals("branchsLocal")) return branchsLocal();
			
			if(key.equals("commits")) return commits();
			if(key.equals("commitsAll")) return commitsAll();
			
			if(key.equals("stashList")) return stashList();
			if(key.equals("genStash")) return genStash();
			
			if(key.startsWith("commitsForBranch:")) return commitsForBranch(m(key));
			if(key.startsWith("commitsForPath:")) return commitsForPathR(m(key));
			if(key.startsWith("commitsForPath1:")) return commitsForPath1(m(key));
			if(key.startsWith("commitsForFile:")) return commitsForFileR(m(key));
			if(key.startsWith("commitsForFile1:")) return commitsForFile1(m(key));
			
			if(key.startsWith("fileForPath:")) return fileForPath(m(key));
			if(key.startsWith("pathForFile:")) return pathForFile(m(key));
			
			if(key.startsWith("branchsForCommit:")) return branchsForCommit(m(key));
			
			
			if(key.equals("keys")) return new String[]{
				"root","status","branch",
				"branchsAll","branchsRemote","branchsLocal",
				"commits","commitsAll","stashList"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		// STATUS
		
		private Map status() throws Exception
		{
			Status status = git.status().call();
			return statusToMap(status);
		}
		
		private Map statusToMap(Status status) throws Exception
		{
			Map map = new HashMap();
			map.put("added",status.getAdded());
			map.put("removed",status.getRemoved());
			map.put("changed",status.getChanged());
			map.put("modified",status.getModified());
			map.put("untracked",status.getUntracked());
			return map;
		}
		
		
		
		// PULL
		
		private void pull() throws Exception
		{cmdPull.p(git);}
		
		private void pull(Object data) throws Exception
		{cmdPull.p(new Object[]{git, data});}
		
		
		
		// PUSH
		
		private void push() throws Exception
		{cmdPush.p(git);}
		
		private void push(Object data) throws Exception
		{cmdPush.p(new Object[]{git, data});}
		
		
		
		// FETCH
		
		private void fetch() throws Exception
		{
			FetchCommand cmd = git.fetch();
			cmd.call();
		}
		
		
		
		
		// STASHES
		
		private List stashList() throws Exception
		{
			Iterable<RevCommit> it = git.stashList().call();
			List list = new ArrayList();
			for(RevCommit commit : it)
			{
				Map m = commitToMap(commit);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private boolean createStash(String stashId) throws Exception
		{
			if(stashId==null) return false;
			if(git.status().call().isClean()) return false;
			git.stashCreate().setIncludeUntracked(true).setWorkingDirectoryMessage(stashId).call();
			return true;
		}
		
		private boolean hasStash(String stashId) throws Exception
		{
			if(stashId==null) return false;
			List<RevCommit> stashes = new ArrayList<>(git.stashList().call());
			for(int i=0;i<stashes.size();i++)
			{
				RevCommit stash = stashes.get(i);
				if(stash.getFullMessage().equals(stashId))
				return true;
			}
			return false;
		}
		
		private boolean dropStash(String stashId) throws Exception
		{
			if(stashId==null) return false;
			List<RevCommit> stashes = new ArrayList<>(git.stashList().call());
			for(int i=0;i<stashes.size();i++)
			{
				RevCommit stash = stashes.get(i);
				if(stash.getFullMessage().equals(stashId))
				{git.stashDrop().setStashRef(i).call();return true;}
			}
			return false;
		}
		
		private void dropAllStashes() throws Exception
		{
			List<RevCommit> stashes = new ArrayList<>(git.stashList().call());
			for(int i=0;i<stashes.size();i++)
			git.stashDrop().setStashRef(i).call();
		}
		
		private boolean applyStash(String stashId) throws Exception
		{
			if(stashId==null) return false;
			List<RevCommit> stashes = new ArrayList<>(git.stashList().call());
			for(int i=0;i<stashes.size();i++)
			{
				RevCommit stash = stashes.get(i);
				if(stash.getFullMessage().equals(stashId))
				{git.stashApply().setStashRef(stash.getName()).call();return true;}
			}
			return false;
		}
		
		private boolean applyDropStash(String stashId) throws Exception
		{
			if(stashId==null) return false;
			List<RevCommit> stashes = new ArrayList<>(git.stashList().call());
			for(int i=0;i<stashes.size();i++)
			{
				RevCommit stash = stashes.get(i);
				if(stash.getFullMessage().equals(stashId))
				{
					git.stashApply().setStashRef(stash.getName()).call();
					git.stashDrop().setStashRef(i).call();
					return true;
				}
			}
			return false;
		}
		
		private String genStash() throws Exception
		{
			String stashId = "auto"+genNum.g();
			boolean done = createStash(stashId);
			return done ? stashId : null;
		}
		
		
		
		
		
		// BRANCHS
		
		private void checkout(String branch) throws Exception
		{
			git.checkout().setName(branch).call();
		}
		
		private void createBranch(String name) throws Exception
		{
			git.branchCreate().setName(name).call();
		}
		
		private String branch() throws Exception
		{
			return repo.getFullBranch();
		}
		
		private List branchsAll() throws Exception
		{
			List list = new ArrayList();
			List<Ref> refs = git.branchList().setListMode(ListMode.ALL).call();
			for(Ref ref : refs) list.add(ref.getName());
			return list;
		}
		
		private List branchsRemote() throws Exception
		{
			List list = new ArrayList();
			List<Ref> refs = git.branchList().setListMode(ListMode.REMOTE).call();
			for(Ref ref : refs) list.add(ref.getName());
			return list;
		}
		
		private List branchsLocal() throws Exception
		{
			List list = new ArrayList();
			List<Ref> refs = git.branchList().call();
			for(Ref ref : refs) list.add(ref.getName());
			return list;
		}
		
		private List branchsForCommit(String commit) throws Exception
		{
			List list = new ArrayList();
			List<Ref> refs = git.branchList().setContains(commit).call();
			for(Ref ref : refs) list.add(ref.getName());
			return list;
		}
		
		
		// MERGE
		
		private boolean merge(String startBranch) throws Exception
		{
			MergeCommand mergeCommand = git.merge();
			mergeCommand.include(git.getRepository().findRef(startBranch));
			mergeCommand.setFastForward(MergeCommand.FastForwardMode.FF_ONLY);
			MergeResult result = mergeCommand.call();
			return result.getMergeStatus().isSuccessful();
		}
		
		
		
		// COMMITS
		
		private void commit(Object obj) throws Exception
		{
			if(obj instanceof String) {commit((String) obj);return;}
			if(obj instanceof String[]) {commit((String[]) obj);return;}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private void commit(String message) throws Exception
		{
			git.commit().setMessage(message).call();
		}
		
		private void commit(String[] infos) throws Exception
		{
			if(infos.length!=3) throw new Exception("Wrong infos number: "+infos.length);
			String name = infos[0];
			String email = infos[1];
			String message = infos[2];
			
			git.commit().setAuthor(name,email).setMessage(message).call();
		}
		
		
		private List commits() throws Exception
		{
			Iterable<RevCommit> it = git.log().call();
			List list = new ArrayList();
			for(RevCommit commit : it)
			{
				Map m = commitToMap(commit);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private List commitsAll() throws Exception
		{
			Iterable<RevCommit> it = git.log().all().call();
			List list = new ArrayList();
			for(RevCommit commit : it)
			{
				Map m = commitToMap(commit);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private List commitsForBranch(String name) throws Exception
		{
			List list = new ArrayList();
			ObjectId branch = repo.resolve(name);
			Iterable<RevCommit> it = git.log().add(branch).call();
			for(RevCommit commit : it)
			{
				Map m = commitToMap(commit);
				list.add(m);
			}
			return addIndex(list);
		}
		
		
		
		private List commitsForFile(String file) throws Exception
		{
			String path = pathForFile(file);
			return commitsForPath(path);
		}
		
		private List commitsForFile1(String file) throws Exception
		{
			String path = pathForFile(file);
			File fileObj = new File(file);
			
			List list = new ArrayList();
			Iterable<RevCommit> it = git.log().addPath(path).call();
			for(RevCommit commit : it) 
			{
				Map m = commitToMap(commit);
				m.put("src",srcForCommit(commit,path));
				m.put("file",fileObj);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private String pathForFile(String file) throws Exception
		{
			String r = gitRoot.getAbsolutePath();
			if(!file.startsWith(r)) throw new Exception("File not inside gitRoot: "+file);
			String path = file.substring(r.length()).replace(File.separator,"/");
			return path.startsWith("/") ? path.substring(1) : path;
		}
		
		
		
		private List commitsForPath(String path) throws Exception
		{
			List list = new ArrayList();
			Iterable<RevCommit> it = git.log().addPath(path).call();
			for(RevCommit commit : it)
			{
				Map m = commitToMap(commit);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private List commitsForPathR(String path) throws Exception
		{
			List list = new ArrayList();
			RevCommit start = null;
			do {
				Iterable<RevCommit> it = git.log().addPath(path).call();
				for (RevCommit commit : it) {
					if (list.contains(commit)) {
						start = null;
					} else {
						start = commit;
						list.add(commit);
					}
				}
				if (start == null) return list;
			}
			while ((path = getRenamedPath(start,path)) != null);
			return list;
		}
		
		private String getRenamedPath(RevCommit start, String path) throws Exception
		{
			Iterable<RevCommit> allCommitsLater = git.log().add(start).call();
			for (RevCommit commit : allCommitsLater)
			{
				TreeWalk tw = new TreeWalk(repo);
				tw.addTree(commit.getTree());
				tw.addTree(start.getTree());
				tw.setRecursive(true);
				RenameDetector rd = new RenameDetector(repo);
				rd.addAll(DiffEntry.scan(tw));
				List<DiffEntry> files = rd.compute();
				for(DiffEntry diffEntry : files)
				{
					if((diffEntry.getChangeType() == DiffEntry.ChangeType.RENAME || diffEntry.getChangeType() == DiffEntry.ChangeType.COPY) && diffEntry.getNewPath().contains(path))
					{
						return diffEntry.getOldPath();
					}
				}
			}
			return null;
		}
		
		
		
		
		
		private List commitsForPathR2(String path) throws Exception
		{
			RevWalk rw = new RevWalk(repo);
			DiffCollector diffCollector = new DiffCollector();
			
			DiffConfig dc = config.get(DiffConfig.KEY);
			FollowFilter followFilter = FollowFilter.create(path, dc);
			followFilter.setRenameCallback(diffCollector);
			rw.setTreeFilter(followFilter);
			rw.markStart(rw.parseCommit(repo.resolve(Constants.HEAD)));
			
			List list = new ArrayList();
			for (RevCommit commit : rw)
			{
				Map m = commitToMap(commit);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private List commitsForFileR(String file) throws Exception
		{
			String path = pathForFile(file);
			return commitsForPathR(path);
		}
		
		
		private List commitsForPath1(String path) throws Exception
		{
			File fileObj = fileForPath(path);
			
			List list = new ArrayList();
			Iterable<RevCommit> it = git.log().addPath(path).call();
			for(RevCommit commit : it) 
			{
				Map m = commitToMap(commit);
				m.put("src",srcForCommit(commit,path));
				m.put("file",fileObj);
				list.add(m);
			}
			return addIndex(list);
		}
		
		private File fileForPath(String path) 
		{
			return new File(gitRoot,path.replace("/",File.separator));
		}
		
		
		
		
		
		
		private List commitToParents(RevCommit commit)
		{
			List list = new ArrayList();
			RevCommit[] parents = commit.getParents();
			for(RevCommit parent : parents)
			{
				Map m = commitToMap(parent);
				list.add(m);
			}
			return addIndex(list);
		}
		
		
		private List commitToBranches(Map commit) throws Exception
		{
			R holder = (R) commit.get("holder");
			String name = (String) commit.get("name");
			return (List) holder.r("branchsForCommit:"+name);
		}
		
		
		private Map commitToMap(RevCommit commit)
		{
			Map map = new HashMap();
			map.put("name",commit.getName());
			map.put("author",commit.getAuthorIdent().getName());
			map.put("email",commit.getAuthorIdent().getEmailAddress());
			map.put("time",commit.getAuthorIdent().getWhen());
			map.put("message",commit.getFullMessage());
			map.put("type",Constants.typeString(commit.getType()));
			
			map.put("access",new CommitAccess(commit));
			map.put("parents",new CommitParents(commit));
			map.put("branches",new CommitBranches(map));
			map.put("commit",commit);
			map.put("holder",this);
			return map;
		}
		
		
		private Map refToMap(Ref ref)
		{
			Map map = new HashMap();
			map.put("name",ref.getName());
			map.put("storage",ref.getStorage());
			map.put("id",ref.getObjectId().getName());
			return map;
		}
		
		private String srcForCommit(RevCommit commit, String path) throws Exception
		{
			RevTree tree = commit.getTree();
			TreeWalk treeWalk = new TreeWalk(repo);
			
			treeWalk.addTree(tree);
			treeWalk.setRecursive(true);
			treeWalk.setFilter(PathFilter.create(path));
			
			if(!treeWalk.next()) return null;
			
			ObjectId objectId = treeWalk.getObjectId(0);
			ObjectLoader loader = repo.open(objectId);
			InputStream in = loader.openStream();
			String src = readString(in);
			
			treeWalk.close();
			return src;
		}
		
		private Map srcMapForCommit(RevCommit commit) throws Exception
		{
			RevTree tree = commit.getTree();
			TreeWalk treeWalk = new TreeWalk(repo);
			treeWalk.addTree(tree);
			treeWalk.setRecursive(true);
			
			Map map = new HashMap();
			while(treeWalk.next())
			{
				String path = treeWalk.getPathString();
				ObjectId objectId = treeWalk.getObjectId(0);
				ObjectLoader loader = repo.open(objectId);
				InputStream in = loader.openStream();
				String src = readString(in);
				
				map.put(path,src);
			}
			treeWalk.close();
			return map;
		}
		
		private class CommitAccess implements R, G
		{
			private RevCommit commit;
			
			public CommitAccess(RevCommit commit)
			{this.commit = commit;}
			
			public Object r(String key) throws Exception
			{return srcForCommit(commit,key);}
			
			public Object g() throws Exception
			{return srcMapForCommit(commit);}
		}
		
		private class CommitParents implements G
		{
			private RevCommit commit;
			
			public CommitParents(RevCommit commit)
			{this.commit = commit;}
			
			public Object g() throws Exception
			{return commitToParents(commit);}
		}
		
		private class CommitBranches implements G
		{
			private Map commit;
			
			public CommitBranches(Map commit)
			{this.commit = commit;}
			
			public Object g() throws Exception
			{return commitToBranches(commit);}
		}
	}
	
	
	
	
	
	
	
	private String readString(InputStream is) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		try(InputStreamReader isr = new InputStreamReader(is,"UTF-8"))
		{
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while((line = br.readLine())!=null) sb.append(line+"\n");
		}
		return sb.toString();
	}
	
	
	
	private List addIndex(List list)
	{
		int len = list.size();
		for(int i=0;i<len;i++)
		((Map)list.get(i)).put("index",len-1-i);
		return list;
	}
	
	
	
	private static class DiffCollector extends RenameCallback
	{
		List<DiffEntry> diffs = new ArrayList<>();
		
		public void renamed(DiffEntry diff)
		{diffs.add(diff);}
	}
	
	private static String m(String cmd)
	{
		String[] n = cmd.split(":",2);
		return n[n.length-1];
	}
}