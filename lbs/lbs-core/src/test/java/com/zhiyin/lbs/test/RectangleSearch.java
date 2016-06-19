package com.zhiyin.lbs.test;

import java.util.List;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;

/**
 * 查找矩形内包含的点
 * @author hg
 *
 */
public class RectangleSearch {

	public static void main(String[] args) {
		 RTree<Integer, Point> tree = RTree.star().create();
		 
		 Rectangle bound = null;
		 List<Entry<Integer, Point>> result = null;
		 
		 tree =  tree.add(1 , Geometries.point( 0.2 , 0.3 ));
		 tree =  tree.add(2 , Geometries.point( 1,1 ));
		 tree =  tree.add(3 , Geometries.point( 0.6,1 ));

		 bound = Geometries.rectangle(0,0,1,1);		 
		 result = tree.search(bound).toList().toBlocking().single();


		 for(int i=0; i<result.size();i++){
			 System.out.println(result.get(i).value());
		 }
		 
		 
		 bound = Geometries.rectangle(0.5,0.5,1,1);		 
		 result = tree.search(bound).toList().toBlocking().single();
		 for(int i=0; i<result.size();i++){
			 System.out.println(result.get(i).value());
		 }
		 
		 bound = Geometries.rectangle(0.5,0.5,0.6,0.6);		 
		 result = tree.search(bound).toList().toBlocking().single();
		 for(int i=0; i<result.size();i++){
			 System.out.println(result.get(i).value());
		 }
		
	}

}
