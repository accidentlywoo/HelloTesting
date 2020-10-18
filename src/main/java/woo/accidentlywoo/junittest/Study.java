package woo.accidentlywoo.junittest;

public class Study {
    private StudyStatus status;
    private int limit;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0 보타 커야 한다.");
        }
        this.limit = limit;
    }
    public StudyStatus getStatus(){
        return this.status;
    }

    public int getLimit(){
        return limit;
    }
}
