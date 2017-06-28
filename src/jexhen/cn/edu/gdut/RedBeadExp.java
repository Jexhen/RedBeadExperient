package jexhen.cn.edu.gdut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RedBeadExp {
	private final static int TOTAL_RED_BEANS = 2400;//需要的红豆总数
	private final static int TOTAL_YELLOW_BEANS = 600;//需要的黄豆总数
	private final static int TOTAL_BEANS = 3000;//需要的豆子总数
	private final static int TOTAL_SAMPLE = 100;//每次抽取的样本数
	private final static int OFFSET = 6;//容器产生的样本抽取偏差
	private int amountOfRedBeans = 0;//已经生成的红豆数
	private int amountOfYellowBeans = 0;//已经生成的黄豆数
	private int amountOfBeans = 0;//已经生成的豆子总数
	private List<Bean> beans = new ArrayList<Bean>();//存放豆子的容器
	private static Random rand = new Random(47);//随机数
	
	public void init() {
		//生成的豆子没有达到需要生成的豆子总数时一直循环
		while (amountOfBeans < TOTAL_BEANS) {
			int randNum = rand.nextInt(2);//用来判定本次生成的是黄豆还是红豆，1为红豆，0为黄豆
			//生成的是红豆，并且没有达到红豆需求总数
			if (randNum == 0 && amountOfRedBeans < TOTAL_RED_BEANS) {
				Bean bean = new Bean("red");
				beans.add(bean);
				amountOfRedBeans++;
				amountOfBeans++;
				//生成的是黄豆，并且没有达到黄豆需求总数
			} else if (amountOfYellowBeans < TOTAL_YELLOW_BEANS){
				Bean bean = new Bean("yellow");
				beans.add(bean);
				amountOfYellowBeans++;
				amountOfBeans++;
			}
		}
//		Collections.shuffle(beans, rand);//混乱容器中的豆子
	}
	
	//第一种方法：每次从list抽取一颗豆子放到Set中，所以全部不重复
	private void experient() {
		int isAdd = rand.nextInt(2);//用于判断偏差是加还是减，1为加，0为减
		int totalOfSample = TOTAL_SAMPLE;//本次抽取的样本数
		int sampleRedBeans = 0;//本次抽取的样本红豆总数
		int sampleYellowBeans = 0;//本次抽取的样本黄豆总数
		Set<Bean> sampleBeans = new LinkedHashSet<Bean>();//用来装样本豆子的容器，容器中的所有豆子需不一样
		//修改偏移量
		if (isAdd == 1) {
			totalOfSample += rand.nextInt(OFFSET + 1);
		} else {
			totalOfSample -= rand.nextInt(OFFSET + 1);
		}
		Collections.shuffle(beans, rand);//每进行一次试验之前混乱容器中的豆子
		for (int i = 0; i < totalOfSample; i++) {
			int index = rand.nextInt(TOTAL_BEANS);
			Bean sampleBean = beans.get(index);
			//当不能增加进容器时说明增加的已经存在，需要继续查找
			while (!sampleBeans.add(sampleBean)) {
				index = rand.nextInt(TOTAL_BEANS);
				sampleBean = beans.get(index);
			}
			if (sampleBean.getColor().equals("red")) {
				sampleRedBeans++;
			} else {
				sampleYellowBeans++;
			}
		}
		System.out.println(totalOfSample + "\t" + sampleRedBeans + "\t" + sampleYellowBeans);
	}
	
	//第二种方法：以一颗豆子为基准，全部以递增或者递减方向选取样本数颗豆子
	private void experient2() {
		int isAdd = rand.nextInt(2);//用于判断偏差是加还是减，1为加，0为减
		int totalOfSample = TOTAL_SAMPLE;//本次抽取的样本数
		int sampleRedBeans = 0;//本次抽取的样本红豆总数
		int sampleYellowBeans = 0;//本次抽取的样本黄豆总数
		//修改偏移量
		if (isAdd == 1) {
			totalOfSample += rand.nextInt(OFFSET + 1);
		} else {
			totalOfSample -= rand.nextInt(OFFSET + 1);
		}
		int index = rand.nextInt(TOTAL_BEANS);//随机抽取一颗豆子，以该豆子为基准，全部以递增或者递减方向选取样本数颗豆子
		int isUp = rand.nextInt(2);//判断是要递增选豆子还是递减选豆子，1为递增选豆子，0为递减选豆子
		Bean sampleBean = null;
		Collections.shuffle(beans, rand);//每进行一次试验之前混乱容器中的豆子
		//如果是递增选豆子
		if (isUp == 1) {
			//循环totalOfSample次，抽取那么多次豆子
			for (int i = 0; i < totalOfSample; i++) {
				if ((index + i) < beans.size()) {
					sampleBean = beans.get(index + i);
//System.out.print(index + i + ",");
				} else {
					//如果超过容器最大值则回到容器顶端
					sampleBean = beans.get((index + i) % beans.size());
//System.out.print((index + i) % beans.size() + ",");
				}
				if (sampleBean.getColor().equals("red")) {
					sampleRedBeans++;
				} else {
					sampleYellowBeans++;
				}
			}
		} else {
			for (int i = 0; i < totalOfSample; i++) {
				if ((index - i) > -1) {
					sampleBean = beans.get(index - i);
//System.out.print(index - i + ",");
				} else {
					//如果超过容器的顶端，则回到尾部
					sampleBean = beans.get(beans.size() + (index - i));
//System.out.print(beans.size() + (index - i) + ",");
				}
				if (sampleBean.getColor().equals("red")) {
					sampleRedBeans++;
				} else {
					sampleYellowBeans++;
				}
			}
		}
		System.out.println(totalOfSample + "\t" + sampleRedBeans + "\t" + sampleYellowBeans);
	}
	
	//第三种方法：以一颗豆子为中心，递增递减方向各抽取一半
	private void experient3() {
		int isAdd = rand.nextInt(2);//用于判断偏差是加还是减，1为加，0为减
		int totalOfSample = TOTAL_SAMPLE;//本次抽取的样本数
		int sampleRedBeans = 0;//本次抽取的样本红豆总数
		int sampleYellowBeans = 0;//本次抽取的样本黄豆总数
		//修改偏移量
		if (isAdd == 1) {
			totalOfSample += rand.nextInt(OFFSET + 1);
		} else {
			totalOfSample -= rand.nextInt(OFFSET + 1);
		}
		int index = rand.nextInt(TOTAL_BEANS);//随机抽取一颗豆子，以该豆子为一颗豆子为中心，递增递减方向各抽取一半
		int isUp = rand.nextInt(2);//判断是要选递增选豆子多一个还是选递减豆子多一个，1为递增方向多选1颗，0为递减方向多选1颗
		Bean sampleBean = null;
		Collections.shuffle(beans, rand);//每进行一次试验之前混乱容器中的豆子
		int half = totalOfSample / 2;//样本的一半方便计数
		if (isUp == 1) {
			for (int i = 0; i < totalOfSample; i++) {
				if (i <= half) {
					if ((index + i) < beans.size()) {
						sampleBean = beans.get(index + i);
//System.out.print(index + i + ",");
					} else {
						//如果超过容器尾部则回到顶端
						sampleBean = beans.get((index + i) % beans.size());
//System.out.print((index + i) % beans.size() + ",");
					} 
					
				} else {
					if ((index - i + half) > -1) {
						sampleBean = beans.get(index - i + half);
//System.out.print(index - i + half + ",");
					} else {
						//如果超过容器的顶端，则回到尾部
						sampleBean = beans.get(beans.size() + (index - i + half));
//System.out.print(beans.size() + (index - i + half) + ",");
					}
				}
				if (sampleBean.getColor().equals("red")) {
					sampleRedBeans++;
				} else {
					sampleYellowBeans++;
				}
			}
		} else {
			for (int i = 0; i < totalOfSample; i++) {
				if (i <= totalOfSample / 2) {
					if ((index - i) > -1) {
						sampleBean = beans.get(index - i);
//System.out.print(index - i + ",");
					} else {
						//如果超过容器的顶端，则回到尾部
						sampleBean = beans.get(beans.size() + (index - i));
//System.out.print(beans.size() + (index - i) + ",");
					}
					
				} else {
					if ((index + i - totalOfSample) < beans.size()) {
						sampleBean = beans.get(index + i - half);
//System.out.print(index + i - half + ",");
					} else {
						//如果超过容器最大容量回到顶端
						sampleBean = beans.get((index + i - half) % beans.size());
//System.out.print((index + i - half) % beans.size() + ",");
					}
				}
				if (sampleBean.getColor().equals("red")) {
					sampleRedBeans++;
				} else {
					sampleYellowBeans++;
				}
			}
		}
		System.out.println(totalOfSample + "\t" + sampleRedBeans + "\t" + sampleYellowBeans);
	}
	
	//参数times是需要做实验的次数,参数methodIndex要选择的算法标号
	public void startExprient(int times, int methodIndex) {
		System.out.println("样本数\t红豆数\t黄豆数");
		switch (methodIndex) {
		case 1:
			for (int i = 0; i < times; i++) {
				experient();
			}
			break;
		case 2:
			for (int i = 0; i < times; i++) {
				experient2();
			}
			break;
		case 3:
			for (int i = 0; i < times; i++) {
				experient3();
			}
			break;
		default:
			System.out.println("Synax Error: Can not find this method!");
			break;
		}
	}
	
	public void showBeans() {
		System.out.println(beans);
	}
	
	public static void main(String[] args) {
		RedBeadExp rbe = new RedBeadExp();
		rbe.init();
		rbe.startExprient(20, 1);
		System.out.println("====================================");
		rbe.startExprient(20, 2);
		System.out.println("====================================");
		rbe.startExprient(20, 3);
	}
}
