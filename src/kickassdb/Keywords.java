package kickassdb;

import java.util.ArrayList;

public class Keywords 
{
    /* This class contains an array of all the keywords that will be used
       for syntax highlighting, using both UPPERCASE and lowercase versions
    */
    
    private final ArrayList<String> keywords;
    
    public Keywords()
    {
        keywords = new ArrayList();
        
        /* Add the keywords */
        keywords.add("CREATE");     keywords.add("TABLE");
        keywords.add("PRIMARY");    keywords.add("KEY");
        keywords.add("INSERT");     keywords.add("INTO");
        keywords.add("VALUES");     keywords.add("SELECT");
        keywords.add("FROM");       keywords.add("WHERE");
        keywords.add("AND");        keywords.add("OR");
        keywords.add("INT");        keywords.add("VARCHAR");
        
        /* Add lower cases */
        createLowerCase();
    }
    
    private void createLowerCase()
    {
        ArrayList<String> temp = new ArrayList();
        
        for ( String s : keywords )
            temp.add(s.toLowerCase());
        
        for ( String s : temp )
            keywords.add(s);
    }
    
    public ArrayList<String> getKeywords()
    {
        return keywords;
    }
    
}
