public class SharedObject {

		private int count = 0;

		public void setCount(int c) {
			System.out.println("name:"+ Thread.currentThread().getName());
			System.out.println("Previous value:"+this.getCount());
			count = c;
			System.out.println("name:"+ Thread.currentThread().getName());
			System.out.println("Updated value:"+this.getCount());
			
		}
		
		public int getCount() {
			return count;
		}

	}