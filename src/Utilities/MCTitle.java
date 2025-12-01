package Utilities;

public class MCTitle {

    protected int contentLenght = 32;

    public void outline(String ...title)
    {
        int size = contentLenght;

        //Autoresize frame
        for(int i = 0; i < title.length; i++)
        {
            if ( title[i].length() > contentLenght )
            {
                size = title[i].length() +2;
            }
        }

        MCLine.outlineCornered( size, true );

        for(int i=0;i<title.length;i++)
        {
            MCLine.outlineSides( size, title[i]);
        }

        MCLine.outlineCornered( size, false );
    }

    public void outlineY(String ...title)
    {
        int size = contentLenght;

        //Autoresize frame
        for(int i = 0; i < title.length; i++)
        {
            if ( title[i].length() > contentLenght )
            {
                size = title[i].length() +2;
            }
        }

        MCLine.outlineCornered( size, true );

        for(int i=0;i<title.length;i++)
        {
            MCLine.slides( size, title[i]);
        }

        MCLine.outlineCornered( size, false );
    }
}
