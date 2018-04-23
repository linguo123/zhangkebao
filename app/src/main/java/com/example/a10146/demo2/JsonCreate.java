package com.example.a10146.demo2;

import java.util.Map;

/**
 * Created by 10146 on 2018/4/23.
 */

public class JsonCreate {
        private String Name;
        private String Sex;
        private String School;
        private String Grade;
        private String Clas;
        private Map<String, String> examAdd ;
        private Map<String, String> schoolAdd ;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }


    public String getClas() {
        return Clas;
    }

    public void setClass(String clas) {
        Clas = clas;
    }

    public Map<String, String> getExamAdd() {
        return examAdd;
    }

    public void setExamAdd(Map<String, String> examAdd) {
        this.examAdd = examAdd;
    }

    public String getSchoolAdd(String schoolAdd) {
        return schoolAdd;
    }

    public void setSchoolAdd(Map<String, String> schoolAdd) {
        this.schoolAdd = schoolAdd;
    }

//    public class examAdd {
//           private String Province ="None";
//           private String City;
//           private String Area;
//
//        public String getProvince() {
//            return Province;
//        }
//
//        public void setProvince(String province) {
//            Province = province;
//        }
//        public String getCity() {
//            return City;
//        }
//
//        public void setCity(String city) {
//            this.City = city;
//        }
//
//        public String getArea() {
//            return Area;
//        }
//        public void setArea(String area) {
//            this.Area = area;
//        }
//
//            @Override
//            public String toString() {
//                return "AddressObject [Province=" + Province + ", City=" + City+ ",Area="+ Area+"]\n\n";
//            }
//        }

//    public class schoolAdd {
//        private String Province;
//        private String City;
//        private String Area;
//        @Override
//        public String toString() {
//            return "AddressObject [Province=" + Province + ", City=" + City+ ",Area="+ Area+"]\n\n";
//        }
//    }
//        @Override
//        public String toString() {
//            return "AccountObject [Name=" + Name + ", Sex=" + Sex + ", examAdd=" + examAdd.toString()
//                    + ", schoolAdd=" + schoolAdd.toString() + ",School="+ School + ",Grade="+ Grade + ",Class="+ Clas + "]\n\n";
//        }

}
