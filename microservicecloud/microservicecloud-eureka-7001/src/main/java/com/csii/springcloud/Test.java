package com.csii.springcloud;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		//是肯定就是打开附流口水的JFK手机打开附件上雕刻技法是看得见件肯定是积分肯定是积分上课JFK实际付款时间飞快的是JFK但是JFK但是JFK设计费空手道解放考生加分考生的释放
		String msgContent="尊敬的用户，您的尾号为8504的账户发生一笔存款，金额10.00元，余额513970.46元。如有疑问，请致电96888。青海农信提醒您：提升个人安全意识，培养良好支付习惯。";
		String[] ss =  splitSms(msgContent,0);
		int msgCount=0;
		Map param = new HashMap();
		// 长短信支持
		for (int j = 0; j < ss.length; j++) {
			param.put("SID", 1
					+ (ss.length > 1 ? "[" + j + "]" : ""));
			param.put("TITLE", ss[j]);
			System.out.println(ss[j]);
			/*
			 * param.put("DSTADDR", "18911684893"); System.out.println(param.toString());
			 */
			//this.getSqlMap().insert("basex.insertMessagexx",param);
		}
	}
	
	   public static String[] splitSms(String msg,int maxLength) {
	    	if(maxLength<=0) maxLength=70;
	    	int n=0;
	    	String[] ss=null;
	    	int msgLen=msg.length();
	    	if(msgLen<=maxLength) {
	    		n=1;
	    		ss=new String[1];
	    		ss[0]=msg;
	    	}
	    	else {// 限制最多9条
	    		
	    		int w;
	    		// XXX 长短信计算修改
	    		if(true)
	    			w=maxLength-5; // 去掉页编码 ，如(1/2)的长度5
	    		else
	    			w=maxLength-3; // 去掉页号，如(1)的长度3，兼容长短信长度67=70-3
	    		
	    		//解决短信拆分时，如果短信长度是65的整数倍时，拆分不对的问题  董彪  2010-05-10  开始
	    		
	    		/*
	    		 * 长度计算：
	    		 * 		当短信需要分页时，每页短信的可用长度为70-5，即65个字符
	    		 * 		当短信内容的长度为65的整数倍时，如130则分为2页，195则分为3页
	    		 */
	    		
	    		//当短信长度为65的整数倍时，旧的方法会多出1页  2010-05-10
	    		// n=Math.min(msgLen/w+1,Constant.MAX_SMS_CHILDNUM);
	    		
	    		n=Math.min(msgLen%w==0?(msgLen/w):(msgLen/w+1),9);
	    		
	    		//解决短信拆分时，如果短信长度是65的整数倍时，拆分不对的问题  董彪  2010-05-10  结束
	    		
	    		ss=new String[n];
	    		for(int i=0;i<n;i++) {
	        		// XXX 长短信计算修改
	    			if(true)
		    			ss[i]="("+(i+1)+"/"+n+")"+ // 页号
		    					msg.substring(i*w,(i==n-1)?msgLen: (i+1)*w); // 截取的内容
	    			else
		    			ss[i]="("+(i+1)+")"+ // 页号
		    					msg.substring(i*w,(i==n-1)?msgLen: (i+1)*w); // 截取的内容
	    		}
	    	}
	    	return ss;
	    }
}
