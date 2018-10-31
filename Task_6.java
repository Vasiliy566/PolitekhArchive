/*
public class MyList<T> {
	
	Node<T> first;
	
	public void add(T data) {
		Node<T> node = new Node<T>();
		node.data = data;
		node.next = first;
		first = node;
	}
	
	@Override
	public String toString() {
		
		String s = "[";
		
		Node temp = first;
		
		while (temp != null) {
			s += temp.data;
			s += ", ";
			temp = temp.next;
		}
		
		return s + "]";
	}

}*/
class Task_6 {
	public static boolean isSubstring(String origin, String sub) {
		for (int i = 0; i <= origin.length() - sub.length(); i++) {
			if (sub.charAt(0) == origin.charAt(i)) {
				for (int j = i; j < i + sub.length(); j++) {
					if (sub.charAt(j - i) != origin.charAt(j ))
						break;
					if (j == i + sub.length() - 1)
						return true;
				}

			}
		}
		return false;

	}

	public static void main(String[] args) {
		System.out.println(isSubstring("abcdefg" , "ce"));
	}
}