package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class APIInfo {
    //Vector<Object> itemList = new Vector<Object>();
    String urlString;
    String response = "";

    public APIInfo(String apiURL) {
        this.urlString = apiURL;
        
        this.response = this.getApiResponse(urlString);
    }

    public Object[] getMoreNews() {

        ArrayList<String> links = new ArrayList<>();

        if(this.response == "") {
            links.add("");
            links.add("story");
            return links.toArray();
        }

        JSONObject obj = new JSONObject(this.response);

        Object linkString = "";

        if(obj.isNull("videos")) {
            JSONArray headlinesArr = obj.getJSONArray("headlines");
            JSONObject headlinesObj = headlinesArr.getJSONObject(0);

            JSONArray videosArr = headlinesObj.getJSONArray("video");
            JSONArray relatedArr = headlinesObj.getJSONArray("related");

            if(relatedArr.isEmpty() && videosArr.isEmpty()) {
                linkString = headlinesObj.getString("story");
                links.add(linkString.toString());
                links.add("story");
                
                return links.toArray();
            }
            else if (headlinesObj.has("story")) {
                linkString = headlinesObj.getString("story");
                links.add( linkString.toString() );
                links.add("story");
            }
            else if(videosArr.isEmpty()) {
                JSONArray relatedArray = headlinesObj.getJSONArray("related");

                JSONObject relatedObj = relatedArray.getJSONObject(0);

                JSONObject linksObj = relatedObj.getJSONObject("links");

                JSONObject apiObject = linksObj.getJSONObject("api");

                JSONObject newsObject = apiObject.getJSONObject("news");

                linkString = newsObject.getString("href");

                this.response = getApiResponse(linkString.toString());

                links.add(linkString.toString());
                links.add("video");
            }
        }
        else {
            JSONArray videosArr = obj.getJSONArray("videos");

            JSONObject videosObj = videosArr.getJSONObject(0);

            JSONObject linksObj = videosObj.getJSONObject("links");
    
            JSONObject sourceObj = linksObj.getJSONObject("source");

            JSONObject mezzanineObj = sourceObj.getJSONObject("mezzanine");

            linkString = mezzanineObj.getString("href");

            links.add(linkString.toString());
            links.add("video");
        }


        return links.toArray();
    }
    

    public String getNewsImg() {
        ArrayList<String> apiItems = new ArrayList<>();
        String link = "";

        JSONObject obj = new JSONObject(this.response);
        JSONArray leaguesArr = obj.getJSONArray("leagues");
        JSONObject leaguesObj = leaguesArr.getJSONObject(0);
        JSONArray logosArr = leaguesObj.getJSONArray("logos");
        JSONObject logosObj = logosArr.getJSONObject(0);
        link = logosObj.getString("href");

        return link;
    }


    public String getLeagueLogo() {
        ArrayList<String> apiItems = new ArrayList<>();
        String link = "";

        JSONObject obj = new JSONObject(this.response);
        JSONArray leaguesArr = obj.getJSONArray("leagues");
        JSONObject leaguesObj = leaguesArr.getJSONObject(0);
        JSONArray logosArr = leaguesObj.getJSONArray("logos");
        JSONObject logosObj = logosArr.getJSONObject(0);
        link = logosObj.getString("href");

        return link;
    }

    public ArrayList<ArrayList<Object>> getESPNStandings() { 
        ArrayList<ArrayList<Object>> apiItems = new ArrayList<>();

        JSONObject obj = new JSONObject(this.response);

        if(obj.isNull("children")) {
            return null;
        }
      
        JSONArray childrenArr = obj.getJSONArray("children");
        int teamNum = 0;

        for(int i=0; i < childrenArr.length(); i++) {
            JSONObject childrenObj = childrenArr.getJSONObject(i);

            if(childrenObj.isNull("standings")) {
                return null;
            }

            JSONObject standingsObj = childrenObj.getJSONObject("standings");

            if(standingsObj.isNull("entries")) {
                return null;
            }
            JSONArray entriesArr = standingsObj.getJSONArray("entries");

            for(int j = 0; j < entriesArr.length(); j++) {

                apiItems.add(new ArrayList<Object>());

                JSONObject entriesObj = entriesArr.getJSONObject(j);
                JSONObject teamObj = entriesObj.getJSONObject("team");

                String id = teamObj.getString("id");
                String teamName = teamObj.getString("displayName");
                apiItems.get(teamNum).add(id);

                String logoImg;

                //Some teams dont have logo link
                if(teamObj.isNull("logos")) {
                    logoImg = "Tally/imgs/Retro Vintage Football Club Badge Logo.png";
                }
                else {
                    JSONArray logoArr = teamObj.getJSONArray("logos");

                    JSONObject logosObj = logoArr.getJSONObject(0);

                    logoImg = logosObj.getString("href");
                }
                apiItems.get(teamNum).add(logoImg);
                apiItems.get(teamNum).add(teamName);
        
                JSONArray statsArr = entriesObj.getJSONArray("stats");

                for(int k = 0; k < statsArr.length(); k++) {
                    JSONObject statsObj = statsArr.getJSONObject(k);

                    String statName = "";
                    if(statsObj.has("displayName")) {
                        statName = statsObj.getString("displayName");
                    }
                    else {
                        statName = statsObj.getString("name");
                    }
                 
                    Object statValue = statsObj.get("displayValue");
    
                    apiItems.get(teamNum).add(statName);
                    apiItems.get(teamNum).add(statValue);
                }
                teamNum++;
            }
        }

        return apiItems;
    }



    public String getAPIInfo() {
        return this.response;
    }


    public Object [][] getESPNNews(String arrName, String itemName) {
        ArrayList<ArrayList<Object>> apiItems = new ArrayList<>();

        JSONObject obj = new JSONObject(this.response);
    
        JSONArray arr = obj.getJSONArray("articles");

        Object description;
        Object img;
        Object moreNewsURL;
       
       for(int i = 0; i < arr.length(); i++)
       {
            apiItems.add(new ArrayList<Object>());

            JSONObject articlesObj = arr.getJSONObject(i);

            if(articlesObj.isNull("description")) {
                description = "Click View More For Full Story";
            }
            else {
                description = articlesObj.get("description");
            }

            JSONArray imgsArr = articlesObj.getJSONArray("images");

            if(imgsArr.isEmpty()) {
                img = "file:///C:/Users/Dan/Documents/GitHub/finalproject-ahllvmp-NotificationUpdated/SE-Project/Tally/imgs/Retro%20Vintage%20Football%20Club%20Badge%20Logo.png";
            }
            else {
                JSONObject urlObject = imgsArr.getJSONObject(0);
                img = urlObject.get("url");
            }
        
            JSONObject linksObj = articlesObj.getJSONObject("links");

            if(linksObj.isNull("api")) {
                moreNewsURL = "";
            }
            else {
                JSONObject apiObj = linksObj.getJSONObject("api");

                JSONObject newsObj = apiObj.getJSONObject("news");
    
                moreNewsURL = newsObj.get("href");
            }

            apiItems.get(i).add(description);
            apiItems.get(i).add(img);
            apiItems.get(i).add(moreNewsURL);
            //apiItems.add(a);
       }
       
        Object items [][] = new Object[arr.length()][3];
        for(int i = 0; i < items.length; i++) {
            items[i][0] = apiItems.get(i).get(0);
            items[i][1] = apiItems.get(i).get(1);
            items[i][2] = apiItems.get(i).get(2);
        }
        //Object items [] = apiItems.toArray();
        return items;
    }


    public ArrayList<ArrayList<String>> getESPNMatchesAPI(String itemName) {

        //Create 2D Array
        ArrayList<ArrayList<String>> apiItems = new ArrayList<ArrayList<String>>();
        String teamName;
        String desc;
        String date;
        String stat;
   
        JSONObject obj = new JSONObject(this.response);
        JSONArray arr = obj.getJSONArray("events");

        int teamNum = 0;
        //Search in events jsonarray
        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj2 = arr.getJSONObject(i);
            date = obj2.getString("date");

            //Search in competitions jsonarray
            JSONArray arr2 = obj2.getJSONArray("competitions");
            for(int j = 0; j < arr2.length(); j++) 
            {
                JSONObject obj3 = arr2.getJSONObject(j);
                JSONObject obj31 = obj3.getJSONObject("status");
                JSONObject obj311 = obj31.getJSONObject("type");
                
                desc = obj311.getString("description");
          
                //Search in competitors jsonarray
                JSONArray arr3 = obj3.getJSONArray("competitors");

                for(int k = 0; k < arr3.length(); k++) 
                {
                    apiItems.add(new ArrayList<String>());
                    apiItems.get(teamNum).add(date);
                    apiItems.get(teamNum).add(desc);

                    obj3 = arr3.getJSONObject(k);

                    JSONObject obj4 = obj3.getJSONObject("team");
                    teamName = obj4.getString("displayName");
                    apiItems.get(teamNum).add(teamName);

                    Object f = obj3.get("score");
                    apiItems.get(teamNum).add(f.toString());

                    obj3 = arr3.getJSONObject(k);
                    JSONArray arr4 = obj3.getJSONArray("statistics");
                    //Search in statistics jsonarray
                    for(int l = 0; l < arr4.length(); l++) 
                    {
                        obj31 = arr4.getJSONObject(l);
                        stat = obj31.getString("name");
                        apiItems.get(teamNum).add(stat);
                        stat = obj31.getString("displayValue");
                        apiItems.get(teamNum).add(stat);
                    }
                    teamNum++;
                }
            }
        }
   
        return apiItems;
    }

    public Object [] getAPIItem(String itemName) {
        ArrayList<Object> apiItems = new ArrayList<>();
        JSONArray arr = new JSONArray(this.response);

        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj = arr.getJSONObject(i);

            Object a = obj.get(itemName);
        
            apiItems.add(a);
        }
        Object items [] = apiItems.toArray();
        return items;
    }


    //public Vector<Object> getAPIListItem(String itemList, String itemName) {
    public Object [] getAPIListItem(String itemList, String itemName) {
        //Vector<Object> itemVector = new Vector<Object>();
        ArrayList<Object> apiItems = new ArrayList<>();

        JSONArray arr = new JSONArray(response);

        //for(int i = 0; i < arr.length(); i++)
        //{
        JSONObject obj = arr.getJSONObject(0);
        JSONArray inArray = obj.getJSONArray(itemList);
        
        /* 
        for(int j = 0; j < inArray.length(); j++)
        {
            JSONObject inArrayObj = inArray.getJSONObject(j);

            Object a = inArrayObj.get(itemName);   
            //itemVector.add(a);
            apiItems.add(a);
   
        }
*/
        for(int j = inArray.length()-1; j >= 0; j--)
        {
            JSONObject inArrayObj = inArray.getJSONObject(j);

            Object a = inArrayObj.get(itemName);   
            apiItems.add(a);
        }

        //}
        //return itemVector;
        Object items [] = apiItems.toArray();
    
        return items;
    }


    public String getApiResponse(String urlString) {
        String response = "";

        try {
            URL url = new URL(urlString);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization","Bearer "+"QSYyBGiMR0yaQibhrndvDA");
            //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5j
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String line;

            while ((line = br.readLine()) != null) {
                response += line;
            }
            conn.disconnect();
        }
        catch (Exception e) {
           System.out.println("Exception in NetClientGet:- " + e);
        }

        return response;
    }
}
