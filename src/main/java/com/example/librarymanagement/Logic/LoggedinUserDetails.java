package com.example.librarymanagement.Logic;

public class LoggedinUserDetails {

    public static class Details{
        static String memberID;
        static String name;
        static String type;

        public static String getMemberID() {
            return memberID;
        }

        public static void setMemberID(String memberID) {
            Details.memberID = memberID;
        }

        public static String getName() {
            return name;
        }

        public static void setName(String name) {
            Details.name = name;
        }

        public static String getType() {
            return type;
        }

        public static void setType(String type) {
            Details.type = type;
        }
    }

}
