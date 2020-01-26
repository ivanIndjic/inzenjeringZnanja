package app;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.Database;
import view.SelectSymptoms;

public class RankingList {

	 public static Map<String, Float> sortByComparator(Map<String, Float> unsortMap, final boolean order)
	    {
	        List<Entry<String, Float>> list = new LinkedList<Entry<String, Float>>(unsortMap.entrySet());
	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<String, Float>>()
	        {
	            public int compare(Entry<String, Float> o1,
	                    Entry<String, Float> o2)
	            {
	                if (order)
	                {
	                    return o1.getValue().compareTo(o2.getValue());
	                }
	                else
	                {
	                    return o2.getValue().compareTo(o1.getValue());
	                }
	            }
	        });
	        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
	        for (Entry<String, Float> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        return sortedMap;
	    }

	    public static void printMap(Map<String, Float> map)
	    {
	        for (Entry<String, Float> entry : map.entrySet())
	        {
	            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
	        }
	    }

	    public boolean proveraRazlikeVerovatnocaZaDaljaIspitivanja(Map<String, Float> map) {
	    	boolean povratna=true;
	    	Float vrednost1=0f;
	    	Float vrednost2=0.0f;
	    	int brojac=0;
	    	 for (Entry<String, Float> entry : map.entrySet())
		        {
		            if(brojac==0) {
		            	SelectSymptoms.bolest1=entry.getKey();
		            	vrednost1=entry.getValue();
		            }else if(brojac==1) {
		            	SelectSymptoms.bolest2=entry.getKey();
		            	vrednost2=entry.getValue();
		            }else if(brojac==2) {
		            	SelectSymptoms.bolest3=entry.getKey();
		            }brojac++;
		        }
	    	if(vrednost1>vrednost2+0.3) {
	    		povratna=false;
	    	}

	    	return povratna;
	    }

	    public String prve2Naziv(Map<String, Float> map,Set<String> selektovani) {

	    	Set<String> ispis=new HashSet<String>();
	    	ArrayList<String> prve2=new ArrayList<String>();
	    	int brojac=0;
	    	 for (Entry<String, Float> entry : map.entrySet())
		        {
		            prve2.add(entry.getKey());
		            brojac++;
		            if(brojac>=2) {
		            	break;
		            }
		        }
	    	 Database bp=new Database();
	    	 HashMap<String,ArrayList<String>> bolesti=bp.getBolesti();
	    	 for(String jedna:prve2) {
	    		if(bolesti.containsKey(jedna)) {
	    			System.out.println(jedna);
	    			ArrayList<String> simptomi=bolesti.get(jedna);
	    			List<String> list = new ArrayList<String>();
	    			list=simptomi;
	    			for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
	    			    String value = iterator.next();
	    			    for(String selektovan: selektovani) {
	    			    	if (value.equals(selektovan)) {
	    			    		iterator.remove();
	    			    	}
	    			    }
	    			}
	    			for(String ss:list) {
	    				ispis.add(ss);
	    			}
	    		}
	    	 }
	    	 String ispiss="";
	    	 if(ispis.size()>0) {
		    	 ispiss+="Are you sure you don't have: ";
		    	 for(String is:ispis) {
		    		 ispiss+=is;
		    		 ispiss+=" ,";
		    	 }
		    	 int upitnik=ispiss.lastIndexOf(',');
		 		ispiss=ispiss.substring(0, upitnik);
		 		ispiss+="?";
	    	}


	    	 return ispiss;
	    }




}
