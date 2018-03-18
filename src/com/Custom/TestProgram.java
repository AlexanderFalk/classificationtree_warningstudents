package com.Custom;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestProgram {

    public static void main(String[] args) {

    }


    public static LinkedList<CustomTree<String>> setup() {
        LinkedList<CustomTree<String>> links = new LinkedList<>();
        String questionOne = "read textbook";
        String questionTwo = "hand ins made in time";
        String questionThree = "attend lectures";
        String questionFour = "make exercises";

        String endpointPass = "You should be able to pass the exam";
        String enpointFail = "You could easily fail the exam";

        CustomTree<String> classificationTree = new CustomTree<>(questionOne);
        links.add(classificationTree);
        {
            CustomTree<String> branchOne = classificationTree.add(questionOne, "no");
            {

            }

            CustomTree<String> branchTwo = classificationTree.add(questionOne, "yes");
            {
                CustomTree<String> branchTwoOne = branchTwo.add(questionTwo, "no");
                {

                }

                CustomTree<String> branchTwoTwoOne = branchTwo.add(questionTwo, "yes");
                {
                    CustomTree<String> branchOneThreeOne = branchTwoTwoOne.add(questionThree, "no");
                    CustomTree<String> branchTwoThreeTwo = branchTwoTwoOne.add(questionThree, "yes");
                    {
                        CustomTree<String> branchTwoFourOne = branchTwoThreeTwo.add(questionFour, "yes");
                        {

                        }
                        CustomTree<String> branchTwoFourTwo = branchTwoThreeTwo.add(questionFour, "no");
                    }
                }
            }
        }

        return links;
    }

}
