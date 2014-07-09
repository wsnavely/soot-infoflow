package soot.jimple.infoflow.data.pathBuilders;

import soot.jimple.infoflow.solver.IInfoflowCFG;


/**
 * Common interface for all path builder factories
 * 
 * @author Steven Arzt
 */
public interface IPathBuilderFactory {
	
	/**
	 * Creates a new path builder
	 * @param maxThreadNum The maximum number of threads to use
	 * @param icfg The interprocedural CFG to use
	 * @return The newly created path builder
	 */
	public IAbstractionPathBuilder createPathBuilder
			(int maxThreadNum, IInfoflowCFG icfg);

}
