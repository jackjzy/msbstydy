package com.ruoyi.common.utils.eqb.hz.key;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @description 关键字处理辅助类
 * @author 宫清
 * @date 2019年8月2日 上午9:36:39
 * @since JDK1.7
 */
public class KeyParserHelper {

	private KeyParserHelper() {
	}

	// ------------------------------公有方法start--------------------------------------------

	/**
	 * @description 获取json下keyword关键字与坐标位置集合关联的map
	 * @param json json字符串
	 * @param page 页码,从1 开始
	 * @return
	 * @date 2019年8月2日 上午10:09:42
	 * @author 宫清
	 */
	private static Map<String, List<PosBean>> filterKeyMap(JSONObject json, Integer page) {
		Map<String, List<PosBean>> map = Maps.newHashMap();
		if (json == null || json.isEmpty()) {
			return map;
		}
		JSONArray dataArr = json.getJSONArray("data");
		if (dataArr.isEmpty() || dataArr.size() == 0) {
			return map;
		}

		for (int i = 0; i < dataArr.size(); i++) {
			JSONObject jobj = dataArr.getJSONObject(i);
			String key = jobj.getString("keyword"); // 关键字
			JSONArray posArr = jobj.getJSONArray("positionList");

			List<PageBean> pageList = posArr.toJavaList(PageBean.class);
			Collections.sort(pageList);
			
			page = page == null ? pageList.get(pageList.size() - 1).getPageIndex() : page;
			for (PageBean pageBean : pageList) {
				if (pageBean.getPageIndex() == page) {
					map.put(key, buildPosList(pageBean.getCoordinateList()));
				}
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String s = "{\r\n" + "	\"code\": 0,\r\n" + "	\"message\": \"成功\",\r\n" + "	\"data\": [{\r\n"
				+ "		\"keyword\": \"测试点\",\r\n" + "		\"fileId\": \"1a289e701af44bc79e10d63aac021ab6\",\r\n"
				+ "		\"positionList\": [{\r\n" + "			\"pageIndex\": 1,\r\n"
				+ "			\"coordinateList\": [{\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 75.64538\r\n" + "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 106.83962\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.00003,\r\n" + "				\"posy\": 138.03387\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 169.22812\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.00003,\r\n" + "				\"posy\": 200.42236\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.000046,\r\n"
				+ "				\"posy\": 231.61661\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.00003,\r\n" + "				\"posy\": 278.40796\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 309.60217\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.00003,\r\n" + "				\"posy\": 403.206\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.00002,\r\n"
				+ "				\"posy\": 434.4002\r\n" + "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 465.59442\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.00003,\r\n" + "				\"posy\": 496.78864\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 527.98285\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.00003,\r\n" + "				\"posy\": 559.17706\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 590.3713\r\n" + "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 621.5655\r\n" + "			}, {\r\n" + "				\"posx\": 90.00003,\r\n"
				+ "				\"posy\": 652.7597\r\n" + "			}, {\r\n"
				+ "				\"posx\": 90.000015,\r\n" + "				\"posy\": 683.9539\r\n" + "			}]\r\n"
				+ "		}, {\r\n" + "			\"pageIndex\": 4,\r\n" + "			\"coordinateList\": [{\r\n"
				+ "				\"posx\": 89.99997,\r\n" + "				\"posy\": 86.062355\r\n"
				+ "			}, {\r\n" + "				\"posx\": 89.99997,\r\n"
				+ "				\"posy\": 179.66621\r\n" + "			}, {\r\n"
				+ "				\"posx\": 89.99996,\r\n" + "				\"posy\": 210.86046\r\n"
				+ "			}, {\r\n" + "				\"posx\": 89.99997,\r\n"
				+ "				\"posy\": 242.0547\r\n" + "			}, {\r\n" + "				\"posx\": 89.99997,\r\n"
				+ "				\"posy\": 273.24893\r\n" + "			}, {\r\n"
				+ "				\"posx\": 89.99997,\r\n" + "				\"posy\": 304.44315\r\n"
				+ "			}, {\r\n" + "				\"posx\": 89.99997,\r\n"
				+ "				\"posy\": 335.63736\r\n" + "			}, {\r\n"
				+ "				\"posx\": 89.99997,\r\n" + "				\"posy\": 366.83157\r\n"
				+ "			}, {\r\n" + "				\"posx\": 89.99997,\r\n"
				+ "				\"posy\": 398.0258\r\n" + "			}, {\r\n" + "				\"posx\": 89.99997,\r\n"
				+ "				\"posy\": 429.22\r\n" + "			}, {\r\n"
				+ "				\"posx\": 89.999985,\r\n" + "				\"posy\": 460.4142\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.0,\r\n" + "				\"posy\": 522.81323\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.0,\r\n" + "				\"posy\": 554.00745\r\n"
				+ "			}, {\r\n" + "				\"posx\": 90.0,\r\n" + "				\"posy\": 647.6113\r\n"
				+ "			}, {\r\n" + "				\"posx\": 89.99999,\r\n"
				+ "				\"posy\": 678.80554\r\n" + "			}, {\r\n" + "				\"posx\": 90.0,\r\n"
				+ "				\"posy\": 709.99976\r\n" + "			}, {\r\n" + "				\"posx\": 90.0,\r\n"
				+ "				\"posy\": 741.194\r\n" + "			}]\r\n" + "		}, {\r\n"
				+ "			\"pageIndex\": 3,\r\n" + "			\"coordinateList\": [{\r\n"
				+ "				\"posx\": 90.0,\r\n" + "				\"posy\": 741.194\r\n" + "			}]\r\n"
				+ "		}]\r\n" + "	}]\r\n" + "}";
		System.out.println(filterKeyMap(JSONObject.parseObject(s), 4));
	}

	// ------------------------------公有方法end----------------------------------------------

	// ------------------------------私有方法start--------------------------------------------

	/**
	 * @description 获取某keyword下坐标位置集合
	 * @param posArr
	 * @return
	 * @date 2019年8月2日 上午10:07:50
	 * @author 宫清
	 */
	private static List<PosBean> buildPosList(List<PosBean> list) {
		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		Collections.sort(list);
		Iterable<PosBean> itr = Iterables.filter(list, Predicates.equalTo(list.get(0)));
		return Lists.newArrayList(itr);
	}

	// ------------------------------私有方法end----------------------------------------------

}
