import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator extends RecursiveTask<Long> {

	/**
	 * 测试fork/join框架
	 */
	private static final long serialVersionUID = 2132138123819238L;
	private Long start;
	private Long end;
	private final Long THRESHOLD =10000l;
	
	public ForkJoinCalculator(Long start, Long end) {
		this.start = start;
		this.end = end;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	@Override
	protected Long compute() {
		Long length = end-start;
		if(length<=THRESHOLD) {
			Long sum = 0l;
			for(Long i = start;i<=end;i++) {
				 sum += i;
			}
			return sum;
		}else {
			Long middle = (start+end)/2;
			ForkJoinCalculator left = new ForkJoinCalculator(start,middle);
			left.fork();//尽心拆分，并加入到子线程中
			ForkJoinCalculator right = new ForkJoinCalculator(middle+1,end);
			right.fork();
			return left.join()+right.join();//将计算的结果进行合并
			
		}
		
	}

}
