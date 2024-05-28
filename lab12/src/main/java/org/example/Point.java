package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Point {
    private int x;
    private int y;

    public void getName(){
        System.out.println("This is a point");
    }

}
