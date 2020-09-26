package by.bsuir.pokos.message;

import java.io.Serializable;

public class User implements Serializable{
        
        static final long serialVersionUID = 3432063776451490808L;
        
        private int id;
        private int postID;
        private String login;
        private String password;   
        private String name;

        public User(int id, int postID, String login, String password, String name) {
            this.id = id;
            this.postID = postID;
            this.login = login;
            this.password = password;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPostID() {
            return postID;
        }

        public void setPostID(int postID) {
            this.postID = postID;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        
    }