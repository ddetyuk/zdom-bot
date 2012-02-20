package com.ddetyuk.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.AmfAction;
import com.exadel.flamingo.flex.messaging.util.StringUtil;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class RoomsAction extends AmfAction {


	private static Logger logger = Logger.getLogger(RoomsAction.class.toString());

	protected User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int getRequestData(OutputStream os) {
		HashMap<String, Object> request = new HashMap<String, Object>();
		request.put("action", "rooms");
		request.put("session", user.getSession().toHashMap());
		serialize(os, request);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResponceData(InputStream in) throws ActionException {
		
		HashMap<String, Object> responce = (HashMap<String, Object>)unserialize(in);
		
		logger.log(Level.FINE, "Responce data:" + StringUtil.toString(responce));

		if (null != responce.get("error")) {
			throw new ActionException((String) responce.get("error"));
		}
		
		if (null != responce.get("rooms")) {
			HashMap<String, Object> user = (HashMap<String, Object>) responce.get("rooms");
			if (null != user.get("rooms")) {
				Object[] rooms = (Object[]) user.get("rooms");
				for (Object data : rooms) {
					HashMap<Object, Object> hashMap = (HashMap<Object, Object>) data;
					logger.log(Level.FINE, "needs:" + StringUtil.toString(hashMap.get("needs")));
					logger.log(Level.FINE, "items:" + StringUtil.toString(hashMap.get("items")));
					//WARNING: item_counts:[{amount=9, id=400, etime=0, master_level=0, room_id=760, mode_id=1}, {amount=12, id=401, etime=280, master_level=1, room_id=760, mode_id=1}, {amount=15, id=402, etime=260, master_level=2, room_id=760, mode_id=1}, {amount=18, id=403, etime=240, master_level=3, room_id=760, mode_id=1}, {amount=22, id=404, etime=220, master_level=4, room_id=760, mode_id=1}, {amount=9, id=405, etime=0, master_level=0, room_id=760, mode_id=3}, {amount=12, id=406, etime=280, master_level=1, room_id=760, mode_id=3}, {amount=15, id=407, etime=260, master_level=2, room_id=760, mode_id=3}, {amount=18, id=408, etime=240, master_level=3, room_id=760, mode_id=3}, {amount=22, id=409, etime=220, master_level=4, room_id=760, mode_id=3}, {amount=9, id=410, etime=0, master_level=0, room_id=760, mode_id=2}, {amount=12, id=411, etime=280, master_level=1, room_id=760, mode_id=2}, {amount=15, id=412, etime=260, master_level=2, room_id=760, mode_id=2}, {amount=18, id=413, etime=240, master_level=3, room_id=760, mode_id=2}, {amount=22, id=414, etime=220, master_level=4, room_id=760, mode_id=2}, {amount=9, id=415, etime=0, master_level=0, room_id=760, mode_id=4}, {amount=12, id=416, etime=280, master_level=1, room_id=760, mode_id=4}, {amount=15, id=417, etime=260, master_level=2, room_id=760, mode_id=4}, {amount=22, id=418, etime=220, master_level=4, room_id=760, mode_id=4}, {amount=18, id=419, etime=240, master_level=3, room_id=760, mode_id=4}, {amount=9, id=420, etime=0, master_level=0, room_id=760, mode_id=14}, {amount=15, id=421, etime=260, master_level=2, room_id=760, mode_id=14}, {amount=18, id=422, etime=240, master_level=3, room_id=760, mode_id=14}, {amount=22, id=423, etime=220, master_level=4, room_id=760, mode_id=17}, {amount=9, id=424, etime=0, master_level=0, room_id=760, mode_id=17}, {amount=9, id=425, etime=0, master_level=0, room_id=760, mode_id=16}, {amount=9, id=426, etime=0, master_level=0, room_id=760, mode_id=21}, {amount=9, id=427, etime=0, master_level=0, room_id=760, mode_id=9}, {amount=9, id=428, etime=0, master_level=0, room_id=760, mode_id=18}, {amount=12, id=429, etime=280, master_level=1, room_id=760, mode_id=14}, {amount=12, id=430, etime=280, master_level=1, room_id=760, mode_id=17}, {amount=12, id=431, etime=280, master_level=1, room_id=760, mode_id=16}, {amount=12, id=432, etime=280, master_level=1, room_id=760, mode_id=21}, {amount=12, id=433, etime=280, master_level=1, room_id=760, mode_id=9}, {amount=12, id=434, etime=280, master_level=1, room_id=760, mode_id=18}, {amount=15, id=435, etime=260, master_level=2, room_id=760, mode_id=9}, {amount=15, id=436, etime=260, master_level=2, room_id=760, mode_id=18}, {amount=15, id=437, etime=260, master_level=2, room_id=760, mode_id=17}, {amount=15, id=438, etime=260, master_level=2, room_id=760, mode_id=16}, {amount=15, id=439, etime=260, master_level=2, room_id=760, mode_id=21}, {amount=18, id=440, etime=240, master_level=3, room_id=760, mode_id=9}, {amount=18, id=441, etime=240, master_level=3, room_id=760, mode_id=18}, {amount=18, id=442, etime=240, master_level=3, room_id=760, mode_id=16}, {amount=18, id=443, etime=240, master_level=3, room_id=760, mode_id=17}, {amount=18, id=444, etime=240, master_level=3, room_id=760, mode_id=21}, {amount=22, id=445, etime=220, master_level=4, room_id=760, mode_id=9}, {amount=22, id=446, etime=220, master_level=4, room_id=760, mode_id=18}, {amount=22, id=447, etime=220, master_level=4, room_id=760, mode_id=14}, {amount=22, id=448, etime=220, master_level=4, room_id=760, mode_id=16}, {amount=22, id=449, etime=220, master_level=4, room_id=760, mode_id=21}, {amount=10, id=676, etime=120, master_level=0, room_id=760, mode_id=6}, {amount=10, id=677, etime=120, master_level=1, room_id=760, mode_id=6}, {amount=10, id=678, etime=120, master_level=2, room_id=760, mode_id=6}, {amount=10, id=679, etime=120, master_level=3, room_id=760, mode_id=6}, {amount=10, id=680, etime=120, master_level=4, room_id=760, mode_id=6}]
					logger.log(Level.FINE, "item_counts:" + StringUtil.toString(hashMap.get("item_counts")));
					
				}
				
			}
			if (null != user.get("phenomenons")) {
				//WARNING: phenomenons:[{lock_help=0, energy_need_mod=12, wall_post_text=, lock_bonus=0, del_bonus_id=0, need_artikul_count=1, id=1, title=Феномен "Туземный идол". , level=6, description=Внимание! В комнате обнаружен феномен, который ускоряет время в комнате в 2 раза. Пока вы не исследуете или не уберете его, затраты энергии в комнате будут больше на %s баллов, show_artikul_id=0, hide_time=0, show_time=0, rooms=[Ljava.lang.Object;@25de2fe5, need_artikul_id=0, show_start_quest_id=0, timer_factor=2, flags=0, animation=IDOL, energy_need_nroom_mod=12, mode_type_id=14, type_id=1, picture=, show_fin_quest_id=0, wall_post_picture=Pic75_Phen_Idol.jpg, create_cnt=0, smodes=[Ljava.lang.Object;@1e1a76cf}, {lock_help=0, energy_need_mod=10, wall_post_text=, lock_bonus=0, del_bonus_id=0, need_artikul_count=1, id=3, title=Феномен "Блуждающий огонек". , level=5, description=Внимание! В комнате обнаружен феномен, который ускоряет время в комнате в 2 раза. Пока вы не исследуете или не уберете его, затраты энергии в комнате будут больше на %s баллов, show_artikul_id=0, hide_time=0, show_time=0, rooms=[Ljava.lang.Object;@5b6e1f79, need_artikul_id=0, show_start_quest_id=0, timer_factor=2, flags=0, animation=FLARE, energy_need_nroom_mod=10, mode_type_id=16, type_id=1, picture=, show_fin_quest_id=0, wall_post_picture=Pic75_Phen_Light.jpg, create_cnt=0, smodes=[Ljava.lang.Object;@1ad46fd4}, {lock_help=0, energy_need_mod=15, wall_post_text=, lock_bonus=0, del_bonus_id=0, need_artikul_count=1, id=4, title=Феномен "Туман"., level=7, description=Внимание! В комнате обнаружен феномен, который ускоряет время в комнате в 2 раза. Пока вы не исследуете или не уберете его, затраты энергии в комнате будут больше на %s баллов, show_artikul_id=0, hide_time=0, show_time=0, rooms=[Ljava.lang.Object;@70f9d50e, need_artikul_id=0, show_start_quest_id=0, timer_factor=2, flags=0, animation=SMOKE, energy_need_nroom_mod=15, mode_type_id=9, type_id=1, picture=, show_fin_quest_id=0, wall_post_picture=Pic75_Phen_Fog.jpg, create_cnt=0, smodes=[Ljava.lang.Object;@2212c414}, {lock_help=0, energy_need_mod=20, wall_post_text=, lock_bonus=0, del_bonus_id=939, need_artikul_count=0, id=5, title=Феномен "Летающая тарелка", level=8, description=Внимание! В комнате обнаружен феномен, который ускоряет время в комнате в 2 раза и переворачивает комнату. Энергии нужно на %s баллов больше, show_artikul_id=0, hide_time=0, show_time=0, rooms=[Ljava.lang.Object;@7e070e85, need_artikul_id=0, show_start_quest_id=0, timer_factor=2, flags=0, animation=UFO, energy_need_nroom_mod=20, mode_type_id=5, type_id=1, picture=, show_fin_quest_id=0, wall_post_picture=Pic75_Phen_UFO.jpg, create_cnt=0, smodes=[Ljava.lang.Object;@14a7e67}]
				logger.log(Level.FINE, "phenomenons:" + StringUtil.toString(user.get("phenomenons")));
			}
			if (null != user.get("monster_types")) {
				logger.log(Level.FINE, "data:" + StringUtil.toString(user.get("monster_types")));
			}
			if (null != user.get("monsters")) {
				logger.log(Level.FINE, "monsters:" + StringUtil.toString(user.get("monsters")));
			}
			if (null != user.get("mode_types")) {
				logger.log(Level.FINE, "mode_types:" + StringUtil.toString(user.get("mode_types")));
			}
			if (null != user.get("monster_stat")) {
				logger.log(Level.FINE, "monster_stat:" + StringUtil.toString(user.get("monster_stat")));
			}
			if (null != user.get("ml_params")) {
				logger.log(Level.FINE, "ml_params:" + StringUtil.toString(user.get("ml_params")));
			}
		}

	}
	
	@Override
	public String toString() {
		return "Action User rooms";
	}
}
