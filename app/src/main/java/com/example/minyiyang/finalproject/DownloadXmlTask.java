//package com.example.minyiyang.finalproject;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import android.view.View;
//import android.widget.ProgressBar;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import static android.view.View.VISIBLE;
//
///**
// * Created by minyiyang on 2018-04-15.
// */
//
//public class DownloadXmlTask extends AsyncTask<String, Integer, String> {
//            private String multi_q;
//            private String ans1;
//            private String ans2;
//            private String ans3;
//            private String ans4;
//            private String correct;
//
//            private String num_q;
//            private String num_ans;
//            private String acc;
//
//            private String tf_q;
//            private String tf_ans;
//
//            DownloadXmlTask(ProgressBar pb){
//                pb=(ProgressBar) findViewById(R.id.pb);
//            }
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(String... args) {
//                try {
//                    for (String siteUrl : args) {
//                        URL url = new URL(siteUrl);
//                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                        InputStream iStream = urlConnection.getInputStream();
//
//                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//                        factory.setNamespaceAware(false);
//                        XmlPullParser xpp = factory.newPullParser();
//                        xpp.setInput(iStream, "UTF-8");
//
//                        int eventType = xpp.getEventType();
//                        while (eventType != XmlPullParser.END_DOCUMENT) {
//                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
//                                if (xpp.getName().equals("MultipleChoiceQuestion")) {
//                                    correct = xpp.getAttributeValue(null, "correct");
//                                    Log.i("XML Message:", correct);
//                                    multi_q = xpp.getAttributeValue(null, "question");
//                                    Log.i("XML Message:", multi_q);
//                                }else if(xpp.getName().equals("Answer")){
//                                    ans1 = xpp.getText();
//                                    Log.i("XML Message:", ans1);
//                                }else if(xpp.getName().equals("Answer")){
//                                    ans2 = xpp.getText();
//                                    Log.i("XML Message:", ans2);
//                                }else if(xpp.getName().equals("Answer")){
//                                    ans3 = xpp.getText();
//                                    Log.i("XML Message:", ans3);
//                                }else if(xpp.getName().equals("Answer")){
//                                    ans4 = xpp.getText();
//                                    Log.i("XML Message:", ans4);
//                                    Thread.sleep(500);
//                                    publishProgress(33);
//                                }
//                                else if (xpp.getName().equals("NumericQuestion")) {
//                                    acc = xpp.getAttributeValue(null, "accuracy");
//                                    Log.i("XML Message:", acc);
//                                    num_q = xpp.getAttributeValue(null, "question");
//                                    Log.i("XML Message:", num_q);
//                                    num_ans = xpp.getAttributeValue(null, "answer");
//                                    Log.i("XML Message:", num_ans);
//                                    Thread.sleep(500);
//                                    publishProgress(66);
//                                } else if (xpp.getName().equals("TrueFalseQuestion")) {
//                                    tf_q = xpp.getAttributeValue(null, "question");
//                                    Log.i("XML Message:", tf_q);
//                                    tf_ans = xpp.getAttributeValue(null, "answer");
//                                    Log.i("XML Message:", tf_ans);
//                                    Thread.sleep(500);
//                                    publishProgress(100);
//                                }
//
//                            }
//                            eventType = xpp.next();
//                        }
//                    }
//                } catch (MalformedURLException e1) {
//                    e1.printStackTrace();
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                } catch (XmlPullParserException e3) {
//                    e3.printStackTrace();
//                } catch (InterruptedException e4) {
//                    e4.printStackTrace();
//                }
//                return "Finished";
//            }
//
//            @Override
//            protected void onProgressUpdate(Integer... progress) {
//                pb.setVisibility(VISIBLE);
//                pb.setProgress(progress[0]);
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                pb.setVisibility(View.INVISIBLE);
//                //INSERT DATA
//            }
//
//}
