package com.zhiyin.lbs.test.rtree;

import static com.github.davidmoten.rtree.geometry.Geometries.rectangle;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

/**
 * 将Rtree序列化
 * @author hg
 *
 */
public class RtreeSerializableTest {

	public static void main(String[] args) {
		 Rectangle a = rectangle(10, 10, 50, 50);

	        
	        RTree<Object, Geometry> tree = RTree.create();
	        tree.add(1,a);
	        

	        try{   
	            FileOutputStream fs = new FileOutputStream("foo.ser");   
	            ObjectOutputStream os =  new ObjectOutputStream(fs);   
	            os.writeObject(tree);   
	            os.close();   
	        }catch(Exception ex){   
	            ex.printStackTrace();   
	        }   
	}

}
