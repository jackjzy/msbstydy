package com.ruoyi.common.utils.eqb.hz.key;

/**
 * @description 坐标javaBean
 * @author 宫清
 * @date 2019年8月2日 上午9:41:46
 * @since JDK1.7
 */
public class PosBean  implements Comparable<PosBean> {
	//X坐标
	private float posX;
	//Y坐标
	private float posY;
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	@Override
	public String toString() {
		return "PosBean [posX=" + posX + ", posY=" + posY + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(posX);
		result = prime * result + Float.floatToIntBits(posY);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj instanceof PosBean) {
			PosBean pos = (PosBean)obj;
			if(this.posX == pos.getPosX() && this.posY == pos.getPosY())
				return true;
		}
		return false;
	}
	@Override
	public int compareTo(PosBean o) {
		if(this.posX > o.getPosX()) {
			return -1;
		}
		if(this.posX < o.getPosX()) {
			return 1;
		}
		if(this.posX == o.getPosX()) {
			if(this.posY > o.getPosY()) {
				return 1;
			}
			if(this.posY < o.getPosY()) {
				return -1;
			}
			return 0;
		}
		return 0;
	}
}
