package journal;

import java.io.*;
public class Dream implements Serializable  {
	private static final long serialVersionUID = 962986944040606875L;
	private String dreamContents;
	public Dream(String dream) {
		dreamContents = dream;
	}
	public String viewDream() {
		return dreamContents;
	}
}
