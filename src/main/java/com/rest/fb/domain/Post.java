package com.rest.fb.domain;

import java.util.List;

public class Post {

    private String message;

    private String id;

    private Reactions reactions;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }


    private class Reactions{
         List<Object> data;

        private class Summary{

            private int total_count;
            private String viewer_reaction;

            public int getTotal_count() {
                return total_count;
            }

            public void setTotal_count(int total_count) {
                this.total_count = total_count;
            }

            public String getViewer_reaction() {
                return viewer_reaction;
            }

            public void setViewer_reaction(String viewer_reaction) {
                this.viewer_reaction = viewer_reaction;
            }
        }

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }
    }
}
