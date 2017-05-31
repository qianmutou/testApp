package com.testapp.engine;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.testapp.R;
import com.testapp.bean.Item;

public class TestEngine {
	private int totalNum;
	private static TestEngine engine;
	private static JsonObject jsonObject;
	private Item item;
	
	
	private TestEngine(){
		engine = this;
	}
	
	public static TestEngine getTestEngine(Context ctx){
		JsonParser parser = new JsonParser();
		InputStreamReader isr = new InputStreamReader(ctx.getResources().openRawResource(R.raw.test));
		jsonObject = (JsonObject) parser.parse(isr);
		return engine;
	}
	
	public int getTotalNum(){
		totalNum = jsonObject.get("totalNum").getAsInt();
		return totalNum;
	}
	
	public Item getItem(int order){
		item = new Item();
		JsonArray items = jsonObject.get("item").getAsJsonArray();
		JsonObject itemJson = (JsonObject) items.get(order);
		item.order = itemJson.get("order").getAsInt();
		item.title = itemJson.get("title").getAsString();
		item.isSolo = itemJson.get("isSolo").getAsBoolean();
		
		JsonArray solutionsArray = itemJson.get("solutions").getAsJsonArray();
		item.solutionNum = solutionsArray.size();
		item.solutions = new String[item.solutionNum];
		for (int i = 0; i < item.solutionNum; i++) {
			item.solutions[i] = solutionsArray.get(i).getAsString();
		}
		solutionsArray = null;
		
		JsonArray answersArray = itemJson.get("answers").getAsJsonArray();
		item.answersNum = answersArray.size();
		item.answers = new String[item.answersNum];
		for (int i = 0; i < item.answersNum; i++) {
			item.answers[i] = answersArray.get(i).getAsString();
		}
		answersArray = null;
		
		
		return item;
	}
}
