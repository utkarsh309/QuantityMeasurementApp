package com.quantitymeasurementapp;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {

        return q1.equals(q2);
    }

    public static <U extends IMeasurable>
    Quantity<U> demonstrateConversion(Quantity<U> q, U target) {

        return q.convertTo(target);
    }

    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {

        return q1.add(q2);
    }

    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {

        return q1.add(q2, target);
    }

    public static void main(String[] args) {

    	//Length Unit
        Quantity<LengthUnit> l1 =
                new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(l1.equals(l2));

        Quantity<LengthUnit> result =l1.add(l2, LengthUnit.FEET);

        System.out.println(result);
        
        System.out.println(l1.subtract(l2));
        System.out.println(l1.subtract(l2, LengthUnit.INCHES));
        
        

        //Weight unit
        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));
        
        System.out.println(w1.subtract(w2));
        System.out.println(w1.divide(w2));
        
        
        //Volume unit
        Quantity<VolumeUnit> v1=new Quantity<>(1.0,VolumeUnit.GALLON);
        
        Quantity<VolumeUnit> v2=new Quantity<>(3.785,VolumeUnit.LITER);
        
        System.out.println(v1.equals(v2));
        System.out.println(v1.add(v2));
        System.out.println(v1.convertTo(VolumeUnit.MILLILITER));
        
        System.out.println(v1.subtract(v2));
        System.out.println(v1.divide(v2));
        
        //Temperature Unit
        Quantity<TemperatureUnit> t1 =new Quantity<>(0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

        System.out.println(t1.equals(t2));
        
        Quantity<TemperatureUnit> temp =new Quantity<>(100, TemperatureUnit.CELSIUS);

        System.out.println(temp.convertTo(TemperatureUnit.FAHRENHEIT));
        
//        temp.add(new Quantity<>(50, TemperatureUnit.CELSIUS));
    }
}