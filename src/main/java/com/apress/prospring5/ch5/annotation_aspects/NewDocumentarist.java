package com.apress.prospring5.ch5.annotation_aspects;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch5.GrammyGuitaristInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("documentarist")
public class NewDocumentarist {
    protected GrammyGuitaristInterface guitarist;

    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(GrammyGuitaristInterface guitarist) {
        this.guitarist = guitarist;
    }
}
