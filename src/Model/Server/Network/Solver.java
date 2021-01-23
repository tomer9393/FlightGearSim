package Model.Server.Network;

public interface Solver<Problem,Solution> {
	public Solution Solve(Problem p);
}
