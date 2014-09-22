package com.example.fuzzylogicmodule;

import com.example.fuzzylogicmodule.Model.MembershipFunction;
import com.jjoe64.graphview.GraphView.GraphViewData;

public class GraphData {
	MembershipFunction mMF;
	String paramsAsString;
	public GraphData(MembershipFunction mf, String str){
		this.mMF = mf;
		this.paramsAsString = str;
	}
	public GraphViewData[] generate(){
		String mfName = this.mMF.getMemfuncName();
		GraphViewData gvd[] = null;
		if(mfName.equals("Triangular")){
			gvd = new GraphViewData[3];
			double x1 = Double.parseDouble(this.mMF.getMemfuncParams()[0]);
			double y1 = Double.parseDouble(this.mMF.getMemfuncParams()[1]);
			double x2 = Double.parseDouble(this.mMF.getMemfuncParams()[2]);
			double y2 = Double.parseDouble(this.mMF.getMemfuncParams()[3]);
			double x3 = Double.parseDouble(this.mMF.getMemfuncParams()[4]);
			double y3 = Double.parseDouble(this.mMF.getMemfuncParams()[5]);
			gvd[0] = new GraphViewData(x1, y1);
			gvd[1] = new GraphViewData(x2, y2);
			gvd[2] = new GraphViewData(x3, y3);			
		}
		else if(mfName.equals("Trapezoidal")){
			gvd = new GraphViewData[4];
			double x1 = Double.parseDouble(this.mMF.getMemfuncParams()[0]);
			double y1 = Double.parseDouble(this.mMF.getMemfuncParams()[1]);
			double x2 = Double.parseDouble(this.mMF.getMemfuncParams()[2]);
			double y2 = Double.parseDouble(this.mMF.getMemfuncParams()[3]);
			double x3 = Double.parseDouble(this.mMF.getMemfuncParams()[4]);
			double y3 = Double.parseDouble(this.mMF.getMemfuncParams()[5]);
			double x4 = Double.parseDouble(this.mMF.getMemfuncParams()[6]);
			double y4 = Double.parseDouble(this.mMF.getMemfuncParams()[7]);
			gvd[0] = new GraphViewData(x1, y1);
			gvd[1] = new GraphViewData(x2, y2);
			gvd[2] = new GraphViewData(x3, y3);
			gvd[3] = new GraphViewData(x4, y4);
		}else if(mfName.equals("Normal")){
			gvd = new GraphViewData[100];
			double mew = Double.parseDouble(paramsAsString.split(",")[0]);
			double sigma = Double.parseDouble(paramsAsString.split(",")[1]);
			double x = 0.0;
			for(int i=0;i<100;i++){
				double y=0.0;
				double temp = 1 / (Math.sqrt(2 * Math.PI)*sigma);
				double temp2 = 1 / (Math.exp(Math.pow((x - mew), 2.0)/(2 * Math.pow(sigma, 2.0))));
				gvd[i] = new GraphViewData(x,temp * temp2);
				x = x+0.01;
			}
		}
		return gvd;
	}

}
