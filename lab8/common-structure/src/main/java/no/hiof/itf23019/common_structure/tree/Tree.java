package no.hiof.itf23019.common_structure.tree;

public class Tree {
	private TreeNode root;
	
	public Tree()
	{
		
	}
	
	public Tree(TreeNode root)
	{
		this.root = root;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public void unvisit()
	{
		root.unvisit();
		root.unvisitChildren();
	}
}
