package enquirysystemwebapp.dao;

import java.util.List;

import enquirysystemwebapp.models.Batch;

public interface BatchDAO {
	public boolean addBatch(Batch batch);
    public void updateBatch(Batch batch);
    public boolean deleteBatch(int batchId);
    public Batch getBatchById(int batchId);
    public List<Batch> getBatchesByCourseId(int courseId);
}
