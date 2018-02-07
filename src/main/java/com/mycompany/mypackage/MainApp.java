package com.mycompany.mypackage;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public class MainApp
{
    private static final String CLASS_NAME = MainApp.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(CLASS_NAME);

    public static void main( String[] args )
    {
        LOGGER.info("*** START ***");

        try{

            List<Set<Integer>> test = Lists.newArrayList(Sets.newHashSet(1, 2, 3),
                                                        Sets.newHashSet(1, 2),
                                                        Sets.newHashSet(2, 3),
                                                        Sets.newHashSet(2, 4));

            List<Set<Integer>> res = SubsetRemover.purify(test);

            // Should be [1,2,3] and [2,4]
            LOGGER.info(String.format("\ntest=%s\nres=%s", test, res));

            List<Set<String>> test2 = Lists.newArrayList(Sets.newHashSet("a", "b", "c", "d"),
                    Sets.newHashSet("a", "b"),
                    Sets.newHashSet("b", "c", "d"),
                    Sets.newHashSet("c", "d", "e"));

            List<Set<String>> res2 = SubsetRemover.purify(test2);

            // Should be ["a", "b", "c", "d"] and ["c", "d", "e"]
            LOGGER.info(String.format("\ntest2=%s\nres2=%s", test2, res2));

            LOGGER.info("*** SUCCEED ***");
        }
        catch (Throwable th){
            LOGGER.error(String.format("*** ERROR ***\n%s",
                    ExceptionUtils.getStackTrace(th)));
        }

        LOGGER.info("*** END ***");
    }
}
