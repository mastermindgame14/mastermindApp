package com.example.mastermindapp;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleGameActivity extends Activity {
	
	//Imageviews being dragged and dropped 
	private ImageView circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8;
	// Imageviews being dopped onto
	private View pit1, pit2, pit3, pit4;
	
	private int[] pits = new int[13];
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_game);
		
		//counter how many balls of a color are still "needed"
		for(int i=1; i<9; i++) {
			pits[i] = 4;
		}
		// counter to see if there is still space for a ball in a certain pit
		for(int i=9; i<13; i++) {
			pits[i] = 1;
		}
		
		//views to drag 
		circle1 = (ImageView)findViewById(R.id.ImageView1);
		circle2 = (ImageView)findViewById(R.id.ImageView2);
		circle3 = (ImageView)findViewById(R.id.ImageView3);
		circle4 = (ImageView)findViewById(R.id.ImageView4);
		circle5 = (ImageView)findViewById(R.id.ImageView5);
		circle6 = (ImageView)findViewById(R.id.ImageView6);
		circle7 = (ImageView)findViewById(R.id.ImageView7);
		circle8 = (ImageView)findViewById(R.id.ImageView8);
		
		//views to drag onto
		pit1 = findViewById(R.id.Image9);
		pit2 = findViewById(R.id.Image10);
		pit3 = findViewById(R.id.Image11);
		pit4 = findViewById(R.id.Image12);
		
		// set touch listeners
		circle1.setOnTouchListener(new ChoiceTouchListener());
		circle2.setOnTouchListener(new ChoiceTouchListener());
		circle3.setOnTouchListener(new ChoiceTouchListener());
		circle4.setOnTouchListener(new ChoiceTouchListener());
		circle5.setOnTouchListener(new ChoiceTouchListener());
		circle6.setOnTouchListener(new ChoiceTouchListener());
		circle7.setOnTouchListener(new ChoiceTouchListener());
		circle8.setOnTouchListener(new ChoiceTouchListener());
		
		// set drag listeners
		pit1.setOnDragListener(new ChoiceDragListener());
		pit2.setOnDragListener(new ChoiceDragListener());
		pit3.setOnDragListener(new ChoiceDragListener());
		pit4.setOnDragListener(new ChoiceDragListener());
		
	}

	private final class ChoiceTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
		  	if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {		  		
				// drag details 
			  	ClipData data = ClipData.newPlainText("", "");
			  	DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			  	//start dragging the item touched
			  	view.startDrag(data, shadowBuilder, view, 0);
			  	//stop displaying the view where it was before it was dragged
			  	view.setVisibility(View.INVISIBLE);
			  	return true;
		  	} 
		  	else {
			  	return false;
		  	}
	  	}
	}

	class ChoiceDragListener implements OnDragListener {
		Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				//remove dragged view 
				View view = (View) event.getLocalState();
		  		ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				
				LinearLayout container = (LinearLayout) v;
				if (container.getChildCount() > 0) {
					//remove every other item in the new view (there should be just 1)
					container.removeAllViews(); 
					//create a new draggable object in corresponding circle [DOESNT WORK]
			  		//owner.addView(view);
				  	//view.setVisibility(View.VISIBLE);
				}
				
				//... and add the dragged item to the dropbox
				container.addView(view);
			  	view.setVisibility(View.VISIBLE);
			  	
			  	/* game setup evaluation */
			  	
			  	//count how many dropboxes have been filled
			  	LinearLayout supercontainer = (LinearLayout) container.getParent();
			  	int amount = supercontainer.getChildCount();	// 4: first row
			  	int count = 0;
			  	View subview = null;
			  	for (int i=0; i<amount; i++){
			  		subview = supercontainer.getChildAt(i);		
			  		count += ((ViewGroup) subview).getChildCount(); 	// the imageviews
			  	}
			  	count -= 4; // always there...
			  	
			  	//4 circles dropped?
			  	if (count == 4){
				  	//get the item colors
			  		
			  		//send them in its order to the server
			  		
			  		//create a new row where to drop circles and make the old one undroppable
			  	}
			  	
			  	
			  
			  	
			  	break;
			case DragEvent.ACTION_DRAG_ENDED:
				v.setBackground(normalShape);
			default:
			  break;
			}
			return true;
		}
  	}
}