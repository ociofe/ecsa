package main.webapp.show.util;

import java.util.Comparator;
import java.util.logging.Logger;

import javax.annotation.Resource;

import main.webapp.ecsa.hibernate.TranslationSeriesname;
import main.webapp.show.controller.SearchController;


public class TranslationSeriesnameComparator implements Comparator<TranslationSeriesname>
{
	 static Logger LOG = Logger.getLogger( TranslationSeriesnameComparator.class.toString());
	 
	 @Resource
	 private SearchController searchController;
	
	 @Override
    public int compare(TranslationSeriesname c1, TranslationSeriesname c2)
    {
		 
    	 LOG.info(searchController.getKeyWord());
        return c1.getTranslation().compareTo(c2.getTranslation());
    }
}