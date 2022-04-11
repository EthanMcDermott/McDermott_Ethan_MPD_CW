//Ethan McDermott
//Student ID: s2130814

package org.me.gcu.labstuff.mcdermott_ethan_mpd_cw;

public class RssItem {

    private String title;
    private String description;

    public RssItem(String rssTitle, String rssDescription)
    {
        title = rssTitle;
        description = rssDescription;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public void setTitle(String val){
        this.title=val;
    }

    public void setDescription(String val){
        this.description=val;
    }
}
