package com.benqzl.core;

import javax.annotation.Resource;

import com.benqzl.socket.MessageQueue;

public class RainQuartz {

	@Resource
	MessageQueue messageQueue;
	
	public void run(){
		System.out.println("================="+messageQueue.concurrentsaveStPptnR);
		messageQueue.concurrentsaveStPptnR.clear();		
	}
	
}
