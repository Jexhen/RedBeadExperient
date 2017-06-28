package jexhen.cn.edu.gdut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RedBeadExp {
	private final static int TOTAL_RED_BEANS = 2400;//��Ҫ�ĺ춹����
	private final static int TOTAL_YELLOW_BEANS = 600;//��Ҫ�Ļƶ�����
	private final static int TOTAL_BEANS = 3000;//��Ҫ�Ķ�������
	private final static int TOTAL_SAMPLE = 100;//ÿ�γ�ȡ��������
	private final static int OFFSET = 6;//����������������ȡƫ��
	private int amountOfRedBeans = 0;//�Ѿ����ɵĺ춹��
	private int amountOfYellowBeans = 0;//�Ѿ����ɵĻƶ���
	private int amountOfBeans = 0;//�Ѿ����ɵĶ�������
	private List<Bean> beans = new ArrayList<Bean>();//��Ŷ��ӵ�����
	private static Random rand = new Random(47);//�����
	
	public void init() {
		//���ɵĶ���û�дﵽ��Ҫ���ɵĶ�������ʱһֱѭ��
		while (amountOfBeans < TOTAL_BEANS) {
			int randNum = rand.nextInt(2);//�����ж��������ɵ��ǻƶ����Ǻ춹��1Ϊ�춹��0Ϊ�ƶ�
			//���ɵ��Ǻ춹������û�дﵽ�춹��������
			if (randNum == 0 && amountOfRedBeans < TOTAL_RED_BEANS) {
				Bean bean = new Bean("red");
				beans.add(bean);
				amountOfRedBeans++;
				amountOfBeans++;
				//���ɵ��ǻƶ�������û�дﵽ�ƶ���������
			} else if (amountOfYellowBeans < TOTAL_YELLOW_BEANS){
				Bean bean = new Bean("yellow");
				beans.add(bean);
				amountOfYellowBeans++;
				amountOfBeans++;
			}
		}
//		Collections.shuffle(beans, rand);//���������еĶ���
	}
	
	//��һ�ַ�����ÿ�δ�list��ȡһ�Ŷ��ӷŵ�Set�У�����ȫ�����ظ�
	private void experient() {
		int isAdd = rand.nextInt(2);//�����ж�ƫ���Ǽӻ��Ǽ���1Ϊ�ӣ�0Ϊ��
		int totalOfSample = TOTAL_SAMPLE;//���γ�ȡ��������
		int sampleRedBeans = 0;//���γ�ȡ�������춹����
		int sampleYellowBeans = 0;//���γ�ȡ�������ƶ�����
		Set<Bean> sampleBeans = new LinkedHashSet<Bean>();//����װ�������ӵ������������е����ж����費һ��
		//�޸�ƫ����
		if (isAdd == 1) {
			totalOfSample += rand.nextInt(OFFSET + 1);
		} else {
			totalOfSample -= rand.nextInt(OFFSET + 1);
		}
		Collections.shuffle(beans, rand);//ÿ����һ������֮ǰ���������еĶ���
		for (int i = 0; i < totalOfSample; i++) {
			int index = rand.nextInt(TOTAL_BEANS);
			Bean sampleBean = beans.get(index);
			//���������ӽ�����ʱ˵�����ӵ��Ѿ����ڣ���Ҫ��������
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
	
	//�ڶ��ַ�������һ�Ŷ���Ϊ��׼��ȫ���Ե������ߵݼ�����ѡȡ�������Ŷ���
	private void experient2() {
		int isAdd = rand.nextInt(2);//�����ж�ƫ���Ǽӻ��Ǽ���1Ϊ�ӣ�0Ϊ��
		int totalOfSample = TOTAL_SAMPLE;//���γ�ȡ��������
		int sampleRedBeans = 0;//���γ�ȡ�������춹����
		int sampleYellowBeans = 0;//���γ�ȡ�������ƶ�����
		//�޸�ƫ����
		if (isAdd == 1) {
			totalOfSample += rand.nextInt(OFFSET + 1);
		} else {
			totalOfSample -= rand.nextInt(OFFSET + 1);
		}
		int index = rand.nextInt(TOTAL_BEANS);//�����ȡһ�Ŷ��ӣ��Ըö���Ϊ��׼��ȫ���Ե������ߵݼ�����ѡȡ�������Ŷ���
		int isUp = rand.nextInt(2);//�ж���Ҫ����ѡ���ӻ��ǵݼ�ѡ���ӣ�1Ϊ����ѡ���ӣ�0Ϊ�ݼ�ѡ����
		Bean sampleBean = null;
		Collections.shuffle(beans, rand);//ÿ����һ������֮ǰ���������еĶ���
		//����ǵ���ѡ����
		if (isUp == 1) {
			//ѭ��totalOfSample�Σ���ȡ��ô��ζ���
			for (int i = 0; i < totalOfSample; i++) {
				if ((index + i) < beans.size()) {
					sampleBean = beans.get(index + i);
//System.out.print(index + i + ",");
				} else {
					//��������������ֵ��ص���������
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
					//������������Ķ��ˣ���ص�β��
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
	
	//�����ַ�������һ�Ŷ���Ϊ���ģ������ݼ��������ȡһ��
	private void experient3() {
		int isAdd = rand.nextInt(2);//�����ж�ƫ���Ǽӻ��Ǽ���1Ϊ�ӣ�0Ϊ��
		int totalOfSample = TOTAL_SAMPLE;//���γ�ȡ��������
		int sampleRedBeans = 0;//���γ�ȡ�������춹����
		int sampleYellowBeans = 0;//���γ�ȡ�������ƶ�����
		//�޸�ƫ����
		if (isAdd == 1) {
			totalOfSample += rand.nextInt(OFFSET + 1);
		} else {
			totalOfSample -= rand.nextInt(OFFSET + 1);
		}
		int index = rand.nextInt(TOTAL_BEANS);//�����ȡһ�Ŷ��ӣ��Ըö���Ϊһ�Ŷ���Ϊ���ģ������ݼ��������ȡһ��
		int isUp = rand.nextInt(2);//�ж���Ҫѡ����ѡ���Ӷ�һ������ѡ�ݼ����Ӷ�һ����1Ϊ���������ѡ1�ţ�0Ϊ�ݼ������ѡ1��
		Bean sampleBean = null;
		Collections.shuffle(beans, rand);//ÿ����һ������֮ǰ���������еĶ���
		int half = totalOfSample / 2;//������һ�뷽�����
		if (isUp == 1) {
			for (int i = 0; i < totalOfSample; i++) {
				if (i <= half) {
					if ((index + i) < beans.size()) {
						sampleBean = beans.get(index + i);
//System.out.print(index + i + ",");
					} else {
						//�����������β����ص�����
						sampleBean = beans.get((index + i) % beans.size());
//System.out.print((index + i) % beans.size() + ",");
					} 
					
				} else {
					if ((index - i + half) > -1) {
						sampleBean = beans.get(index - i + half);
//System.out.print(index - i + half + ",");
					} else {
						//������������Ķ��ˣ���ص�β��
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
						//������������Ķ��ˣ���ص�β��
						sampleBean = beans.get(beans.size() + (index - i));
//System.out.print(beans.size() + (index - i) + ",");
					}
					
				} else {
					if ((index + i - totalOfSample) < beans.size()) {
						sampleBean = beans.get(index + i - half);
//System.out.print(index + i - half + ",");
					} else {
						//�������������������ص�����
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
	
	//����times����Ҫ��ʵ��Ĵ���,����methodIndexҪѡ����㷨���
	public void startExprient(int times, int methodIndex) {
		System.out.println("������\t�춹��\t�ƶ���");
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
