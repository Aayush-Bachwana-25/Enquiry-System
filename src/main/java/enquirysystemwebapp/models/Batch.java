package enquirysystemwebapp.models;

public class Batch {
	private int id;
	private String name;
    private int courseId;
    private String timings;
	
    public Batch(){
    	super();
    }
    public Batch(int courseId){
    	super();
    	this.courseId = courseId;
    }
    
    public Batch(String name, int courseId,String timings) {
		super();
		this.name = name;
		this.courseId = courseId;
		this.timings=timings;
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", name=" + name + ", courseId=" + courseId + ", timings=" + timings + "]";
	}
	
	
    
    
}
