package ru.rezonans_lab.lordofsands.domain.model;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

@Log4j2
public class TradeChapter extends Chapter {
    private ArrayList<Product> products;
//    private final ArrayList<String> confirmMenu;
//    private final ApplicationEventPublisher eventPublisher;

    //пока такая глава всего одна и новых не предвидится
    //так что мне лень сериализовать список продуктов, будет хардкод
    public TradeChapter() {
        products = createProducts();
//        confirmMenu = new ArrayList<>(2);
//        confirmMenu.add("Да, купить");
//        confirmMenu.add("Посмотреть другие товары");
    }

    @Override
    public float nextChapter(GameState state, int userChoice) {
//        Hero hero = state.getHero();
//        if (!state.isProductSelected()) {
//            if (userChoice == products.size()) {
////                log.info("Покупатель покидает лавку");
//                // Выход из главы
//                return choices.get(0).getId(); // например, MapChapter
//            }
//            // если выбрали товар
//            state.setSelectedProductIndex(userChoice);
//            state.setProductSelected(true);
//            return id; // остаёмся в этой главе
//        } else {
//            // Режим DESCRIPTION (товар выбран)
//            if (userChoice == 0) { // КУПИТЬ
//                Product p = products.get(state.getSelectedProductIndex());
//                if (hero.getBackpack().lookForItem(p.name())) {
////                    eventPublisher.publishEvent(new GameMessageEvent("У вас уже есть " +
//                            p.name()));
//                }
//                if (hero.getMoney() >= p.price()) {
//                    hero.changeMoney(-p.price());
//                    hero.getBackpack().addToBackPack(p.name());
////                    products.remove(state.getSelectedProduct());
//                    // Сообщение об успехе (будет показано в process)
//                } else {
////                    eventPublisher.publishEvent(new GameMessageEvent(
//                            "Увы, вам не хватает денег"));
//                }
//            }
//            // В любом случае возвращаемся к списку
//            state.setProductSelected(false);
//            state.setSelectedProductIndex(-1);
        return choices.get(0).getId();
    }

    @Override
    public GameState process(GameState state) {
//        if (!state.isProductSelected()) {
//            state.setChoices(productsToChoices());
//            state.setTextToPrint(text);
////            state.setProductSelected(true);
//        } else {
//            Product p = products.get(state.getSelectedProductIndex());
//            state.setTextToPrint(p.description());
//            state.setChoices(confirmMenu);
//        }

        //пока непонятно что тут делать в андроид версии
        return state;
    }

//    private ArrayList<String> productsToChoices() {
//        ArrayList<String> choices = new ArrayList<>();
//        for (Product p : products) {
//            choices.add(p.getName());
//        }
//        choices.add("Завершить торговлю");
//        return choices;
//    }

    private ArrayList<Product> createProducts() {//немного хардкода временно
        products = new ArrayList<>(8);
//        products.add(Product.builder()
//                .name("Моток веревки")
//                .description("""
//                        Моток веревки, которая всегда может пригодиться, займет 1 место в вашем заплечном мешке.
//                        Цена - 2 монеты.
//                        """)
//                        .price(2)
//                .build());
//        products.add(Product.builder()
//                .name("Голубь в клетке")
//                .description("""
//                        Почтовый голубь в изящной позолоченной клетке.
//                        Вы можете приобрести клетку с голубем и выпустить птицу на волю.
//                        Голубь будет сопровождать вас, и вы сможете в любой момент отправить его
//                        с посланием в Элгариол.
//                        Цена - 5 монет
//                        """)
//                .price(5)
//                .build());
//        products.add(Product.builder()
//                .name("Накидка от песчаной бури")
//                .description("""
//                        Накидка, по словам торговца, при случае сможет защитить от песчаной бури, а быть может, и не только от нее.
//                        Цена - 3 монеты
//                        """)
//                        .price(3)
//                .build());
//        products.add(Product.builder()
//                .name("Необычный меч")
//                .description("""
//                         Меч, на рукояти которого неизвестные вам письмена вязью.
//                         Рана, нанесенная этим мечом, будет стоить врагу не 2, а 3 СИЛЫ. Решайте, оставите ли свой меч караванщику или будете таскать его с собой, хотя новое оружие явно лучше старого.
//                        Цена - 5 монет
//                        """)
//                .price(5)
//                .build());
//        products.add(Product.builder()
//                .name("Шёлковый пояс")
//                .description("""
//                        Торговец и сам не может толком рассказать, волшебный это пояс или обыкновенный. Однако вышитые на нем руны, безусловно, содержат какую-то магическую силу. Но вот злую или добрую?
//                        Цена - 5 монет
//                        """).price(5)
//                .build());
//        products.add(Product.builder()
//                .name("Флакон")
//                .description("""
//                        Флакон с неизвестной жидкостью.
//                        Торговец говорит, что это очень мощное противоядие, которое может не раз пригодиться в дороге. Верить ему или нет — дело ваше, но проверить это сейчас все равно невозможно.
//                        Цена - 6 монет
//                        """)
//                .price(6)
//                .build());
//        products.add(Product.builder()
//                .name("Рыцарские доспехи (2 монеты)")
//                .description("""
//
//                        """)
//                .build());
        //абсолютно нелепый предмет с точки зрения игровой механики, даже не буду его реализовывать
        return products;
    }
}