
class SimpleParser { 
    private String[] tokens; 
    private String postfix; 
    private String lookahead; 
    //private Automata automata; 
    private int current = 0;
    public SimpleParser(String infix) { 
    tokens = infix.split("\\s+");      
    postfix = "";
    //automata = new Automata();
    lookahead = tokens[current];
    }
    void expr() { 
            term(); 
            while(true) { 
                if(lookahead.compareTo("+") == 0) { 
                match("+"); 
                term(); 
                postfix += " ";
                postfix += "+";
                }    
                else if(lookahead.compareTo("-") == 0) { 
                match("-"); 
                term(); 
                postfix += " ";
                postfix += "-";
                } 
                else return; 
            } 
        
    }
    void term() { 
            factor(); 
            while(true) { 
                if(lookahead.compareTo("*") == 0) { 
                match("*"); 
                factor(); 
                postfix += " ";
                postfix += "*";
                }   
                if(lookahead.compareTo("/") == 0) { 
                match("/"); 
                factor(); 
                postfix += " ";
                postfix += "/";
                }   
                else return; 
            } 
    }
    void factor() { 
        if (lookahead.compareTo("(")==0) {
        match("(");
        expr();
        if(lookahead.compareTo(")")==0)
        match (")");
        }
        else if(Integer.parseInt(lookahead)> -1000000) { 
            postfix += " ";
            postfix += lookahead;
            match(lookahead); 
        } 
        else throw new Error("syntax error"); 
    }

    void match(String t) { 
        if( lookahead.compareTo(t) == 0 ) {
            if(current < tokens.length-1) 
                lookahead = tokens[++current];
        } 
        else throw new Error("syntax error"); 
    }
    public String getPostfix(){
    return postfix; 
    }    
}


