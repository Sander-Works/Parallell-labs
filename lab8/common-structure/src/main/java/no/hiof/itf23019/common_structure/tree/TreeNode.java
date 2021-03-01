package no.hiof.itf23019.common_structure.tree;


import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TreeNode implements Comparable<TreeNode>{
	int id;
    String name;
    private boolean visited;
    private Set<TreeNode> children;
    
    ReentrantReadWriteLock visitedLock;

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
        visited = false;
        
        children = new ConcurrentSkipListSet<TreeNode>();
        
        visitedLock = new ReentrantReadWriteLock();
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisited() {
    	
		visitedLock.readLock().lock();
    	boolean ret = visited;
    	visitedLock.readLock().unlock();
        return ret;
    }

    public void visit() {
    	
    	visitedLock.writeLock().lock();
        visited = true;
        visitedLock.writeLock().unlock();
    }

    public void unvisit() {
    	visitedLock.writeLock().lock();
        visited = false;
        visitedLock.writeLock().unlock();
    }
    
    public void addChild(TreeNode child)
    {
    	children.add(child);
    }
    
    public Set<TreeNode> getChildren()
    {
    	return children;
    }
    
    public void unvisitChildren()
    {
    	for(TreeNode child : children)
    	{
    		child.unvisit();
    		child.unvisitChildren();
    	}
    }

	@Override
	public int compareTo(TreeNode o) {
		return this.id - o.id;
	}
}
