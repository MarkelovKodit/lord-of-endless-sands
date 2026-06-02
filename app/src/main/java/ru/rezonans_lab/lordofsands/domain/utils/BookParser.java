package ru.rezonans_lab.lordofsands.domain.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.rezonans_lab.lordofsands.domain.conditions.*;
import ru.rezonans_lab.lordofsands.domain.model.*;
import ru.rezonans_lab.lordofsands.domain.effects.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BookParser {
    private final static int MAX_SUB_CHAPTERS = 5;

    public static Chapter [][] parse(InputStream inputStream) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(inputStream);
            System.out.println("НАЗВАНИЕ КНИГИ - " + rootNode.get("title").asText());
            return parseChapters(rootNode.get("chapters"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Chapter [][] parseChapters(JsonNode chaptersNode) {
        float maxId = 0;
        for (int i = 0; i < chaptersNode.size(); i++) {
            float id = chaptersNode.get(i).get("id").floatValue();
            if (id > maxId) maxId = id;
        }

        int maxMainId = (int) maxId;
        Chapter[][] chapters = new Chapter[maxMainId + 1][MAX_SUB_CHAPTERS];        Chapter chapter;
        float chapterId;
        System.out.println(chaptersNode.size() + " - от стока глав сейчас есть в json");
        JsonNode thisChapterNode;
        for (int i = 0; i < chaptersNode.size(); i++) {
            ArrayList<Choice> choices = new ArrayList<>();
            ArrayList<Effect> effects;
            ChapterType chapterType;
            thisChapterNode = chaptersNode.get(i);
            chapterId = thisChapterNode.get("id").floatValue();//обязательное поле в JSON

            if (thisChapterNode.has("enemy")) {
                chapter = new FightChapter();
                chapter.setChapterType(ChapterType.FIGHT);
                ((FightChapter) chapter).setEnemy(parseEnemyNode(thisChapterNode.get("enemy")));
            } else if (thisChapterNode.has("gamble")) {
                chapter = new GambleChapter(Gambler.builder()
                        .money(6)
                        .name("ГНОМ")
                        .build());
                chapter.setChapterType(ChapterType.GAMBLE);
                //это пока все равно не нужно но пусть будет
            } else if (thisChapterNode.has("trade")) {
                chapter = new TradeChapter();
                chapter.setChapterType(ChapterType.TRADE);
            }  else {// и так далее до обычного типа главы
                chapter = new RegularChapter();
                chapter.setChapterType(ChapterType.REGULAR);
            }
            chapter.setId(chapterId);
            //извлекаем текст главы
            if (thisChapterNode.has("text")) {
                chapter.setText(thisChapterNode.get("text").asText());
                System.out.println(chapter.getText());
            }

            if (thisChapterNode.has("effects")) {
                effects = parseEffectsNode(thisChapterNode.get("effects"));
                chapter.setEffects(effects);
            }

            if (thisChapterNode.has("condition")) {
                chapter.setCondition(parseConditionsNode(thisChapterNode.get("condition")));
            }
            if (thisChapterNode.has("choices")) {
                JsonNode choicesNode = thisChapterNode.get("choices");
                for (int j = 0; j < choicesNode.size(); j++) {
//                    System.out.println(thisChapterNode.get(i).get("id"));
                    String choiceText = null;
                    if (choicesNode.get(j).has("text")) {
                        choiceText = choicesNode.get(j).get("text").asText();
                    }
                    float choiceId;
                    if (choicesNode.get(j).has("id")) {
                        choiceId = choicesNode.get(j).get("id").floatValue();
                    } else {
                        choiceId = -1;
                    }
                    choices.add(new Choice(choiceId, choiceText));
                }
                System.out.println(choices);
                chapter.setChoices(choices);
            }
            System.out.println(chapter.getText());
            setChapter(chapters, chapter, chapterId);
        }
        return chapters;
    }

    private static void setChapter(Chapter[][] chapters, Chapter chapter, float id) {
        String idStr = String.valueOf(id);
        String[] parts = idStr.split("\\.");
        int mainId = Integer.parseInt(parts[0]);
        int subId = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;

        chapters[mainId][subId] = chapter;
    }

    private static ArrayList<Effect> parseEffectsNode(JsonNode effectsNode) {
        ArrayList<Effect> effects = new ArrayList<>();
        String type;
        for (int i = 0; i < effectsNode.size(); i++) {
            type = effectsNode.get(i).get("type").asText();
            switch (type) {
                case "game_over":
                    effects.add(new GameOver());
                    break;
                case "change_stat":
                    Stat stat = Stat.valueOf(effectsNode.get(i).get("stat").asText());
                    int amount = effectsNode.get(i).get("amount").asInt();
                    effects.add(new ChangeStat(stat, amount));
                    break;
                case "random_health_loss":
                    effects.add(new RandomHealthLoss(effectsNode.get(i).get("xtra_factor").asInt()));
                    break;
                case "add_to_backpack":
                    effects.add(new AddToBackpack(effectsNode.get(i).get("stuff").asText()));
                    break;
                case "clear_luck":
                    effects.add(new ClearLuck());
                    break;
                case "clear_backpack":
                    effects.add(new ClearBackpack());
                    break;
                case "robbery":
                    effects.add(new Robbery());
                    break;
                case "max_health":
                    effects.add(new MaxHealth());
                    break;
                case "curse":
                    effects.add(new Curse());
                    break;
                case "achievement":
                    effects.add(new Achievement(effectsNode.get(i).get("achieve").asText()));
                    break;
                default:
                    System.out.println("неизвестный тип эффекта");
                    break;
            }
        }
        return effects;
    }

    private static Enemy parseEnemyNode(JsonNode enemyNode) {
        String name = enemyNode.get("name").asText();
        int health = enemyNode.get("health").asInt();
        int power = enemyNode.get("power").asInt();
        int dmg = enemyNode.get("dmg").asInt();
        boolean isItSword = enemyNode.get("is_it_sword").asBoolean();
        return new Enemy(name, health, power, dmg, isItSword);
    }

    private static Condition parseConditionsNode(JsonNode conditionNode) {
        Condition condition;
        String type = conditionNode.get("type").asText();
        float[] choices = new float[12];
        switch (type) {
            case "check_stat":
                choices[0] = conditionNode.get("values").get(2).floatValue();
                choices[1] = conditionNode.get("values").get(3).floatValue();
                condition = CheckStat.builder()
                        .name(conditionNode.get("values").get(0).asText())
                        .valueToCompare(conditionNode.get("values").get(1).asInt())
                        .choices(choices)
                        .build();
                break;
            case "use_mind_power":
                JsonNode node = conditionNode.get("values");
                for (int i = 0; i < node.size(); i++) {
                    choices[node.get(i).get("power").asInt()] = node.get(i).get("id").floatValue();
                }
                condition = new RouteCondition(choices, "power");
                break;
            case "check_backpack":
                choices[0] = conditionNode.get("values").get(1).floatValue();
                choices[1] = conditionNode.get("values").get(2).floatValue();
                condition = new CheckBackpack(conditionNode.get("values").get(0).asText(), choices);
                break;
            case "achievement":
                choices[0] = conditionNode.get("values").get(1).floatValue();
                choices[1] = conditionNode.get("values").get(2).floatValue();
                condition = new CheckAchievements(conditionNode.get("values").get(0).asText(), choices);
                break;
            default:
                System.out.println("НЕИЗВЕСТНЫЙ CONDITION в JSON");
                throw new RuntimeException();
        }
        return condition;
    }
}