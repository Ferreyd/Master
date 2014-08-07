package tp3nicolas.bebetes.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tp3nicolas.bebetes.Bebete;
import tp3nicolas.bebetes.BebeteHasard;
import tp3nicolas.bebetes.ChampDeBebetes;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BebeteHasardTest4
{

    private BebeteHasard bebeteHasard = null;
    private ChampDeBebetes champ = new ChampDeBebetes(640, 480, 0);

    int nbIter = 20;


    @Before
    public void setUp() throws Exception
    {        /* position de la bebete par defaut, au centre, angle a 0, vitesse a 10 */
        bebeteHasard = new BebeteHasard(champ, 320, 240, 0.0F, 10.0F, Color.RED);

        //		double olddir = bebeteHasard.getDirectionCourante();

        ArrayList<Bebete> l = new ArrayList<Bebete>();
        l.add(bebeteHasard);

        champ.setDessinables(l);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testEffectueDeplacementMilieu()
    {
        // mise en place : rien a changer, juste sauvegarder les anciennes
        // valeurs pour l'oracle
        double oldvit = bebeteHasard.getVitesseCourante();
        double olddir = bebeteHasard.getDirectionCourante();
        bebeteHasard.effectueDeplacement();

        // oracle
        assertEquals("direction inchangee", olddir, bebeteHasard.getDirectionCourante(), 0.1f);
        assertEquals("vitesse inchangee", oldvit, bebeteHasard.getVitesseCourante(), 0.1f);

        testCap();

    }

    @Test
    public void testEffectueDeplacementDepassementDroit()
    {
        // mise en place
        bebeteHasard.setX(637);
        bebeteHasard.setY(240);
        bebeteHasard.setDirectionCourante((float) Math.PI / 5);
        // test
        bebeteHasard.effectueDeplacement();
        // oracle

        assertEquals("nouvelle direction apres rebond", bebeteHasard.getDirectionCourante(),
                (float) (Math.PI * 4 / 5), 0.1f);
        assertTrue("x revenu dans le champ", bebeteHasard.getX() < 640);
        assertTrue("y plus grand", bebeteHasard.getY() > 240);

        testCap();


    }

    @Test
    public void testEffectueDeplacementDepassementGauche()
    {
        // mise en place
        bebeteHasard.setX(3);
        bebeteHasard.setY(240);
        bebeteHasard.setDirectionCourante(Math.PI * 6 / 5);
        // test
        bebeteHasard.effectueDeplacement();
        // oracle
        assertEquals("nouvelle direction apres rebond", (Math.PI * 9 / 5), bebeteHasard.getDirectionCourante(), 0.1f);
        assertTrue("x revenu dans le champ", bebeteHasard.getX() > 0);
        assertTrue("y plus petit", bebeteHasard.getY() < 240);

        testCap();

    }

    @Test
    public void testEffectueDeplacementDepassementHaut()
    {
        // mise en place
        bebeteHasard.setX(320);
        bebeteHasard.setY(4);
        bebeteHasard.setDirectionCourante((float) Math.PI * 6 / 5);
        // test
        bebeteHasard.effectueDeplacement();
        // oracle
        assertEquals("nouvelle direction apres rebond", bebeteHasard.getDirectionCourante(),
                (float) (Math.PI * 4 / 5), 0.1f);
        assertTrue("x plus petit", bebeteHasard.getX() < 320);
        assertTrue("y revenu dans le champ", bebeteHasard.getY() > 0);


        bebeteHasard.setX(5);
        bebeteHasard.setY(5);
        bebeteHasard.setVitesseCourante(10);

        bebeteHasard.setDirectionCourante((float) Math.PI * 3 / 2);
        // test
        bebeteHasard.effectueDeplacement();

        // oracle
        assertEquals("nouvelle direction apres rebond", (Math.PI / 2), bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        // par dessin on trouve x entre 8 et 9 et y entre 5 et 5
        assertEquals("x revenu dans le champ, x = " + bebeteHasard.getX(), 5, bebeteHasard.getX(), 0.1);
        assertEquals("y revenu dans le champ, y = " + bebeteHasard.getY(), 5, bebeteHasard.getY(), 0.1);

        testCap();


    }

    @Test
    public void testEffectueDeplacementDepassementBas()
    {
        // mise en place
        bebeteHasard.setX(320);
        bebeteHasard.setY(477);
        bebeteHasard.setDirectionCourante((float) Math.PI / 5);
        // test
        bebeteHasard.effectueDeplacement();
        // oracle
        assertEquals("nouvelle direction apres rebond", bebeteHasard.getDirectionCourante(),
                (float) (Math.PI * 9 / 5), 0.1f);
        assertTrue("x plus grand", bebeteHasard.getX() > 320);
        assertTrue("y revenu dans le champ", bebeteHasard.getY() < 480);


        testCap();

    }


    @Test
    public void testEffectueDeplacementCoinHautGaucheAngle45()
    {
        // mise en place
        bebeteHasard.setX(3);
        bebeteHasard.setY(3);
        bebeteHasard.setDirectionCourante((float) Math.PI * 3 / 4);

        // test
        bebeteHasard.effectueDeplacement();


        // oracle
        assertEquals("nouvelle direction apres rebond", (float) (Math.PI / 4), bebeteHasard.getDirectionCourante(),
                0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        assertTrue("x revenu dans le champ, x = " + bebeteHasard.getX(), 0 < bebeteHasard.getX() && bebeteHasard.getX
                () < 12);
        assertTrue("y revenu dans le champ, y = " + bebeteHasard.getY(), 0 < bebeteHasard.getY() && bebeteHasard.getY
                () < 12);


        testCap();

    }

    @Test
    public void testEffectueDeplacementDoubleRebondCoinHautGauche22_5()
    {
        // mise en place
        bebeteHasard.setX(1);
        bebeteHasard.setY(1);
        bebeteHasard.setDirectionCourante((float) Math.PI * 9 / 8); // on se
        // dirige
        // vers le
        // coin
        // haut/gauche

        // test
        bebeteHasard.effectueDeplacement();

        // oracle
        assertEquals("nouvelle direction apres rebond", (Math.PI / 8), bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        // par dessin on trouve x entre 8 et 9 et y entre 2 et 3
        assertTrue("x revenu dans le champ, x = " + bebeteHasard.getX(), 0 < bebeteHasard.getX() && bebeteHasard.getX
                () < 9);
        assertTrue("y revenu dans le champ, y = " + bebeteHasard.getY(), 0 < bebeteHasard.getY() && bebeteHasard.getY
                () < 3);


        bebeteHasard.setX(1);
        bebeteHasard.setY(1);
        bebeteHasard.setDirectionCourante((float) Math.PI * 5 / 4); // on se
        // dirige
        // vers le
        // coin
        // haut/gauche... droit dessus
        bebeteHasard.setVitesseCourante(10d);
        // test
        bebeteHasard.effectueDeplacement();

        // oracle
        assertEquals("nouvelle direction apres rebond", (Math.PI / 4), bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        // par dessin on trouve x entre 8 et 9 et y entre 6.07 et 6.07
        assertEquals("x revenu dans le champ, x = " + bebeteHasard.getX(), 6, bebeteHasard.getX(), 0.1);
        assertEquals("y revenu dans le champ, y = " + bebeteHasard.getY(), 6, bebeteHasard.getY(), 0.1);

        testCap();

    }


    @Test
    public void testEffectueDeplacementDoubleRebondCoinHautDroitAngle45()
    {
        // mise en place
        bebeteHasard.setX(637);
        bebeteHasard.setY(3);
        bebeteHasard.setDirectionCourante((float) Math.PI * 7f / 4f); // on se
        // dirige
        // vers le
        // coin
        // haut/droit

        bebeteHasard.setVitesseCourante(10d);
        // test
        bebeteHasard.effectueDeplacement();

        // x+v*cos(d) = 646.24 --> 635.93
        // y+v*sin(d) = -4.07 -->  4.07

        // oracle
        assertEquals("nouvelle direction apres rebond", (float) (3 * Math.PI / 4),
                bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        assertTrue("x revenu dans le champ, x = " + bebeteHasard.getX(), bebeteHasard.getX() < 640);
        assertTrue("y revenu dans le champ, y = " + bebeteHasard.getY(), 0 < bebeteHasard.getY());


        assertEquals("x revenu dans le champ, x = " + bebeteHasard.getX(), 635.93, bebeteHasard.getX(), 0.1);
        assertEquals("y revenu dans le champ, y = " + bebeteHasard.getY(), 4.07, bebeteHasard.getY(), 0.1);


        testCap();

    }


    @Test
    public void testEffectueDeplacementDoubleRebondCoinHautDroitAngle22_5()
    {
        // mise en place
        bebeteHasard.setX(637);
        bebeteHasard.setY(3);
        bebeteHasard.setDirectionCourante((float) Math.PI * 15f / 8f); // on se
        // dirige
        // vers le
        // coin
        // haut/droit

        bebeteHasard.setVitesseCourante(10d);
        // test
        bebeteHasard.effectueDeplacement();

        // x+v*cos(d) = 646.24 --> 633.76
        // y+v*sin(d) = -0.87 -->  +0.87

        // oracle
        assertEquals("nouvelle direction apres rebond", (float) (7f * Math.PI / 8f),
                bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        assertTrue("x revenu dans le champ, x = " + bebeteHasard.getX(), bebeteHasard.getX() < 640);
        assertTrue("y revenu dans le champ, y = " + bebeteHasard.getY(), 0 < bebeteHasard.getY());


        assertEquals("x revenu dans le champ, x = " + bebeteHasard.getX(), 633.76, bebeteHasard.getX(), 0.1);
        assertEquals("y revenu dans le champ, y = " + bebeteHasard.getY(), 0.87, bebeteHasard.getY(), 0.1);


        testCap();

    }


    @Test
    public void testEffectueDeplacementDoubleRebondCoinBasDroitAngle45()
    {
        // mise en place
        bebeteHasard.setX(637);
        bebeteHasard.setY(477);
        bebeteHasard.setDirectionCourante((float) Math.PI / 4f); // on se
        // dirige
        // vers le
        // coin
        // bas/droit

        // x+v*cos(d) = 646.24 --> 635.93
        // y+v*sin(d) = 484.07 -->  475.93

        bebeteHasard.setVitesseCourante(10d);
        // test
        bebeteHasard.effectueDeplacement();


        // oracle
        assertEquals("nouvelle direction apres rebond", (float) (5 * Math.PI / 4),
                bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        assertTrue("x revenu dans le champ, x = " + bebeteHasard.getX(), bebeteHasard.getX() < 640);
        assertTrue("y revenu dans le champ, y = " + bebeteHasard.getY(), 480 > bebeteHasard.getY());


        assertEquals("x revenu dans le champ, x = " + bebeteHasard.getX(), 635.93, bebeteHasard.getX(), 0.1);
        assertEquals("y revenu dans le champ, y = " + bebeteHasard.getY(), 475.93, bebeteHasard.getY(), 0.1);

        testCap();

    }


    @Test
    public void testEffectueDeplacementDoubleRebondCoinBasGaucheAngle45()
    {
        // mise en place
        bebeteHasard.setX(3);
        bebeteHasard.setY(477);
        bebeteHasard.setDirectionCourante((float) Math.PI * 3f / 4f); // on se
        // dirige
        // vers le
        // coin
        // bas/droit

        bebeteHasard.setVitesseCourante(10d);
        // test
        bebeteHasard.effectueDeplacement();


        // x+v*cos(d) = -4.07 --> 4.07
        // y+v*sin(d) = 484.07 -->  475.93

        // oracle
        assertEquals("nouvelle direction apres rebond", (float) (7 * Math.PI / 4),
                bebeteHasard.getDirectionCourante(), 0.1f);
        // le comportement d'un rebond dans l'angle n'est pas d�fini. Cela
        // d�pendrait effectivement de la forme qu'a l'objet, etc...
        assertTrue("x revenu dans le champ, x = " + bebeteHasard.getX(), bebeteHasard.getX() > 0);
        assertTrue("y revenu dans le champ, y = " + bebeteHasard.getY(), 480 > bebeteHasard.getY());


        assertEquals("x revenu dans le champ, x = " + bebeteHasard.getX(), 4.07, bebeteHasard.getX(), 0.1);
        assertEquals("y revenu dans le champ, y = " + bebeteHasard.getY(), 475.93, bebeteHasard.getY(), 0.1);


        testCap();
    }


    private void testCap()
    {
        double newVitesse = bebeteHasard.getVitesseCourante();
        double newAngle = bebeteHasard.getDirectionCourante();
        int nb_pas = 0;
        while(nb_pas <= nbIter)
        {
            // pour ne pas avoir de changement de direction
            bebeteHasard.setNbTour(0);
            champ.nextStep();
            assertEquals("valeur direction", bebeteHasard.getDirectionCourante(), newAngle, 0.01f);
            assertEquals("valeur direction", bebeteHasard.getVitesseCourante(), newVitesse, 0.01f);

            nb_pas++;
        }
    }
}
