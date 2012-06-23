/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.bl.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import org.courses.whpp.entity.Product;
import org.courses.whpp.entity.Property;
import org.courses.whpp.session.CategoryFacade;

/**
 *
 * @author stvad
 */
public class Searcher
{
    public CategoryFacade wFacade;

    public Searcher()
    {
        wFacade = new CategoryFacade();
    }
    
    public Collection<Product> find(Integer categoryId, Collection<Property> properties)
    {
        Collection<Product> inCategory = wFacade.find(categoryId).getProductList();
        Collection<Product> result = new ArrayList<Product>();
        
       Boolean equal;
        for(Product tproduct: inCategory)
        {
            equal = true;
            for(Property wProperty: tproduct.getCategory().getPropertyList())
            {
                for(Property tProperty: properties)
                {
                    if(wProperty.getPname().equals(tProperty.getPname()))
                    {
                        if(!wProperty.getPvalue().equals(tProperty.getPvalue()))
                            equal = false;
                        break;
                    }
                }
                if(!equal)
                    break;
                
            }
            if(equal)
                result.add(tproduct);
        }
        
        return result;
    }
}
