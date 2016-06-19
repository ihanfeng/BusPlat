package com.zhiyin.lbs.test.rtree;

import static com.github.davidmoten.rtree.geometry.Geometries.rectangle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class Test {

	public static void main(String[] args) throws IOException {
		  Rectangle a = rectangle(10, 10, 50, 50);
	        Rectangle b = rectangle(28.0, 4.0, 34.0, 85.0);
	        
	        RTree<Object, Geometry> tree = RTree.create();
	        tree.add(1,a);
	        tree.add(2, b);
	        
//	        DateUtils.
	       
	        ;
//	        "306.4172189289285 946.0362833946623 306.4172189289285 946.0362833946623\n"
	       for(int i=0; i< 100 ;i++){
	    	   FileUtils.writeStringToFile(new File("test.tmp"), Math.random()*1000 + " " +  Math.random()*1000 + " " + Math.random()*1000 + " " + Math.random()*1000 + "\n",true);
	       
	       }
	       
	       List<String> lines = FileUtils.readLines(new File("test.tmp"));
	       
	       for(int i=0; i<lines.size();i++){
	    	   String str = lines.get(i);
	    	   if(str.trim().length() == 0){
	    		   continue;
	    	   }
	    	   String[] val = str.split("\\s+");
	    	   System.out.println(str);
	    	   Rectangle t = rectangle(Double.valueOf(val[0]), Double.valueOf(val[1]), Double.valueOf(val[2]), Double.valueOf(val[3]));
	    	   tree.add(i,t);
	       }
	        
	        
	}

}
