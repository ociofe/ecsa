package main.webapp.show.controller;

import java.util.Comparator;
import java.util.logging.Logger;

import main.webapp.ecsa.hibernate.TranslationSeriesname;



public class TranslationSeriesnameComparator implements Comparator<TranslationSeriesname>
{
	 static Logger LOG = Logger.getLogger( TranslationSeriesnameComparator.class.toString());
	 
	
	 @Override
    public int compare(TranslationSeriesname c1, TranslationSeriesname c2)
    {
		 SearchController src = new SearchController();
		 LOG.info("keyword: " +src.getKeyWord());
		 int comp1 =  org.apache.commons.lang3.StringUtils.getLevenshteinDistance(c1.getTranslation(), src.getKeyWord());
		 int comp2 =  org.apache.commons.lang3.StringUtils.getLevenshteinDistance(c2.getTranslation(), src.getKeyWord());
		 return comp1 - comp2;
    }
}