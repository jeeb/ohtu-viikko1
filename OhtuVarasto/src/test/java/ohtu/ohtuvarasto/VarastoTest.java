package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void addNegativeAmountOfThings() {
        varasto.lisaaVarastoon(-1);

        // Adding negative amount of things should lead to nothing being added
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void addThingsWithinLimits() {
        varasto.lisaaVarastoon(5);

        // varasto had zero things, thus adding five should give us five in the saldo
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void addTooManyThings() {
        varasto.lisaaVarastoon(9000);

        // varasto overflowed, and anything that went over 10 got thrown into the ether
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void takeNegativeAmountOfThings() {
        varasto.otaVarastosta(-1);

        // Taking negative amount of things should lead to nothing being done
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void takeThingsWithinLimits() {
        // Add 7
        varasto.lisaaVarastoon(7);

        // Take 5
        varasto.otaVarastosta(5);

        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void takeTooManyThings() {
        // Add 5
        varasto.lisaaVarastoon(5);

        // Take 9000
        varasto.otaVarastosta(9000);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}