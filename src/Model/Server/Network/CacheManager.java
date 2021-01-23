package Model.Server.Network;

public interface CacheManager<Problem,Solution> {
	public Boolean Check(Problem in);
	public Solution Extract(Problem in);
	public void Save(Problem in,Solution out);
}
