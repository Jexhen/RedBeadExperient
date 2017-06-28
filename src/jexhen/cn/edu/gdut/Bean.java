package jexhen.cn.edu.gdut;

public class Bean {
	private String color;
	Bean(String color){
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Bean [color=" + color + "]";
	}
}
