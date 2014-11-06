package com.example.l33534.google;

/**
 * Created by L33534 on 10/27/2014.
 */
public class FixLocation/* extends MyLocationOverlay */{
/*
    private boolean bugged = false;
    private Drawable drawable;
    private Paint accuracyPaint;
    private Point center;
    private Point left;
    private int width;
    private int height;
    public FixLocation(Context context, MapView mapView) {
        super(context, mapView);
    }
    @Override
    protected void drawMyLocation(Canvas canvas, MapView mapView,
                                  Location lastFix, GeoPoint myLocation, long when) {
        if(!bugged) {
            try {
                super.drawMyLocation(canvas, mapView, lastFix,
                        myLocation, when);
            } catch (Exception e) {
// we found a buggy phone, draw the location  icons ourselves;
                bugged = true;
            }
        }
        if(bugged) {
            if(drawable == null) {
                accuracyPaint = new Paint();
                accuracyPaint.setAntiAlias(true);
                accuracyPaint.setStrokeWidth(2.0f);
                drawable = mapView.getContext()
                        .getResources().getDrawable(R.drawable.ic_maps_indicator_current_position);
                width = drawable.getIntrinsicWidth();
                height = drawable.getIntrinsicHeight();
                center = new Point();
                left = new Point();
            }
            Projection projection = mapView.getProjection();
            double latitude = lastFix.getLatitude();
            double longitude = lastFix.getLongitude();
            float accuracy = lastFix.getAccuracy();
            float[] result = new float[1];
            Location.distanceBetween(latitude, longitude, latitude,longitude + 1, result);
            float longitudeLineDistance = result[0];
            GeoPoint leftGeo = new GeoPoint((int)(latitude*1e6),
                    (int)((longitude-accuracy/longitudeLineDistance)*1e6));
            projection.toPixels(leftGeo, left);
            projection.toPixels(myLocation, center);
            int radius = center.x - left.x;
            accuracyPaint.setColor(0xff6666ff);
            accuracyPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(center.x, center.y, radius,
                    accuracyPaint);
            accuracyPaint.setColor(0x186666ff);
            accuracyPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(center.x, center.y, radius,
                    accuracyPaint);
            drawable.setBounds(center.x - width/2, center.y -
                    height/2, center.x + width/2, center.y + height/2);
            drawable.draw(canvas);
        }
    }*/
}
