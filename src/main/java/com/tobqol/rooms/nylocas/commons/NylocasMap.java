package com.tobqol.rooms.nylocas.commons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.runelite.api.NpcID;
import net.runelite.api.NullNpcID;

import com.tobqol.api.game.Instance;
import com.google.common.collect.*;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

import static com.google.common.collect.Tables.immutableCell;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum NylocasMap
{
	BOSS_DROPPING_MELEE(NpcID.NYLOCAS_VASILIAS_10786, NpcID.NYLOCAS_VASILIAS, NpcID.NYLOCAS_VASILIAS_10807),
	BOSS_MELEE(NpcID.NYLOCAS_VASILIAS_10787, NpcID.NYLOCAS_VASILIAS_8355, NpcID.NYLOCAS_VASILIAS_10808),
	BOSS_MAGIC(NpcID.NYLOCAS_VASILIAS_10788, NpcID.NYLOCAS_VASILIAS_8356, NpcID.NYLOCAS_VASILIAS_10809),
	BOSS_RANGE(NpcID.NYLOCAS_VASILIAS_10789, NpcID.NYLOCAS_VASILIAS_8357, NpcID.NYLOCAS_VASILIAS_10810),
	DEMI_BOSS_DROPPING_MELEE(NpcID.NYLOCAS_PRINKIPAS),
	DEMI_BOSS_MELEE(NpcID.NYLOCAS_PRINKIPAS_10804),
	DEMI_BOSS_MAGIC(NpcID.NYLOCAS_PRINKIPAS_10805),
	DEMI_BOSS_RANGE(NpcID.NYLOCAS_PRINKIPAS_10806),
	MELEE_SMALL(
			NpcID.NYLOCAS_ISCHYROS_10791,
			NpcID.NYLOCAS_ISCHYROS_8342,
			NpcID.NYLOCAS_ISCHYROS_10791,
			NpcID.NYLOCAS_ISCHYROS_10780,
			NpcID.NYLOCAS_ISCHYROS_8348,
			NpcID.NYLOCAS_ISCHYROS_10797
	),
	RANGE_SMALL(
			NpcID.NYLOCAS_TOXOBOLOS_10775,
			NpcID.NYLOCAS_TOXOBOLOS_8343,
			NpcID.NYLOCAS_TOXOBOLOS_10792,
			NpcID.NYLOCAS_TOXOBOLOS_10781,
			NpcID.NYLOCAS_TOXOBOLOS_8349,
			NpcID.NYLOCAS_TOXOBOLOS_10798
	),
	MAGIC_SMALL(
			NpcID.NYLOCAS_HAGIOS_10776,
			NpcID.NYLOCAS_HAGIOS,
			NpcID.NYLOCAS_HAGIOS_10793,
			NpcID.NYLOCAS_HAGIOS_10782,
			NpcID.NYLOCAS_HAGIOS_8350,
			NpcID.NYLOCAS_HAGIOS_10799
	),
	MELEE_BIG(
			NpcID.NYLOCAS_ISCHYROS_10777,
			NpcID.NYLOCAS_ISCHYROS_8345,
			NpcID.NYLOCAS_ISCHYROS_10794,
			NpcID.NYLOCAS_ISCHYROS_10783,
			NpcID.NYLOCAS_ISCHYROS_8351,
			NpcID.NYLOCAS_ISCHYROS_10800
	),
	RANGE_BIG(
			NpcID.NYLOCAS_TOXOBOLOS_10778,
			NpcID.NYLOCAS_TOXOBOLOS_8346,
			NpcID.NYLOCAS_TOXOBOLOS_10795,
			NpcID.NYLOCAS_TOXOBOLOS_10784,
			NpcID.NYLOCAS_TOXOBOLOS_8352,
			NpcID.NYLOCAS_TOXOBOLOS_10801
	),
	MAGIC_BIG(
			NpcID.NYLOCAS_HAGIOS_10779,
			NpcID.NYLOCAS_HAGIOS_8347,
			NpcID.NYLOCAS_HAGIOS_10796,
			NpcID.NYLOCAS_HAGIOS_10785,
			NpcID.NYLOCAS_HAGIOS_8353,
			NpcID.NYLOCAS_HAGIOS_10802
	),
	PILLAR(NullNpcID.NULL_10790, NullNpcID.NULL_8358, NullNpcID.NULL_10811);

	private final int sm, rg, hm, aggro_sm, aggro_rg, aggro_hm;

	public static final String BOSS_NAME = "Nylocas Vasilias";
	public static final String DEMI_BOSS_NAME = "Nylocas Prinkipas";
	public static final String MELEE_NAME = "Nylocas Ischyros";
	public static final String RANGE_NAME = "Nylocas Toxobolos";
	public static final String MAGIC_NAME = "Nylocas Hagios";

	public static final int BOSS_MAGIC_ANIM = 7989;
	public static final int BOSS_RANGE_ANIM = 7999;
	public static final int BOSS_MELEE_ANIM = 8004;

	public static final int PILLAR_GO_ID = 32862;
	public static final int PILLAR_COLLAPSED_GO_ID = 32864;

	public static final int IMMUNE_GRAPHIC = 1558; // Spawns on the SW tile of the nylo
	public static final int MELEE_SMALL_DESPAWN_GRAPHIC = 1562;
	public static final int RANGE_SMALL_DESPAWN_GRAPHIC = 1563;
	public static final int MAGIC_SMALL_DESPAWN_GRAPHIC = 1564;

	private static final ImmutableMultimap<NylocasMap, Integer> ENUM_MULTIMAP;
	private static final Table<Instance.Mode, Integer, NylocasMap> LOOKUP_TABLE;
	private static final ImmutableMultimap<NylocasType, Integer> TYPE_IDS;

	public static final Color MAGIC_COLOR = new Color(130, 230, 255);
	public static final Color MELEE_COLOR = new Color(255, 188, 188);
	public static final Color RANGE_COLOR = new Color(188, 255, 201);

	static
	{
		ImmutableMultimap.Builder<NylocasMap, Integer> enum_builder = ImmutableListMultimap.builder();
		ImmutableTable.Builder<Instance.Mode, Integer, NylocasMap> t_builder = ImmutableTable.builder();

		for (NylocasMap def : values())
		{
			if (def.sm == -1 && def.rg == -1)
			{
				enum_builder.put(def, def.hm);
				t_builder.put(immutableCell(Instance.Mode.HARD, def.hm, def));
				continue;
			}

			enum_builder.putAll(def, def.sm, def.rg, def.hm);
			t_builder.put(immutableCell(Instance.Mode.STORY, def.sm, def));
			t_builder.put(immutableCell(Instance.Mode.REGULAR, def.rg, def));
			t_builder.put(immutableCell(Instance.Mode.HARD, def.hm, def));

			if (def.aggro_sm == -1 && def.aggro_rg == -1 && def.aggro_hm == -1)
			{
				continue;
			}

			enum_builder.putAll(def, def.aggro_sm, def.aggro_rg, def.aggro_hm);
			t_builder.put(immutableCell(Instance.Mode.STORY, def.aggro_sm, def));
			t_builder.put(immutableCell(Instance.Mode.REGULAR, def.aggro_rg, def));
			t_builder.put(immutableCell(Instance.Mode.HARD, def.aggro_hm, def));
		}

		ENUM_MULTIMAP = enum_builder.build();
		LOOKUP_TABLE = t_builder.build();

		ImmutableMultimap.Builder<NylocasType, Integer> type_ids = ImmutableListMultimap.builder();

		List<NylocasMap> melees = ImmutableList.of(
				BOSS_DROPPING_MELEE,
				BOSS_MELEE,
				BOSS_MAGIC,
				BOSS_RANGE
		);

		melees.forEach(def -> type_ids.putAll(NylocasType.BOSS, def.sm, def.rg, def.hm));

		List<NylocasMap> demis = ImmutableList.of(
				DEMI_BOSS_DROPPING_MELEE,
				DEMI_BOSS_MELEE,
				DEMI_BOSS_MAGIC,
				DEMI_BOSS_RANGE
		);

		demis.forEach(def -> type_ids.putAll(NylocasType.DEMI, def.hm));

		List<NylocasMap> bigs = ImmutableList.of(
				MELEE_BIG,
				RANGE_BIG,
				MAGIC_BIG
		);

		bigs.forEach(def -> type_ids.putAll(NylocasType.BIG, def.sm, def.rg, def.hm, def.aggro_sm, def.aggro_rg, def.aggro_hm));

		List<NylocasMap> smalls = ImmutableList.of(
				MELEE_SMALL,
				RANGE_SMALL,
				MAGIC_SMALL
		);

		smalls.forEach(def -> type_ids.putAll(NylocasType.SMALL, def.sm, def.rg, def.hm, def.aggro_sm, def.aggro_rg, def.aggro_hm));

		TYPE_IDS = type_ids.build();
	}

	public static boolean matchesAnyMode(NylocasMap def, int npcId)
	{
		if (def == null)
		{
			return false;
		}

		return ENUM_MULTIMAP.get(def).contains(npcId);
	}

	@Nullable
	public static NylocasMap queryTable(Instance.Mode mode, int npcId)
	{
		if (mode == null)
		{
			return null;
		}

		return LOOKUP_TABLE.get(mode, npcId);
	}

	@Nullable
	public static Instance.Mode findMode(int npcId)
	{
		return Instance.findFirstMode(mode -> queryTable(mode, npcId) != null);
	}

	public static boolean isBoss(int npcId)
	{
		return TYPE_IDS.get(NylocasType.BOSS).contains(npcId);
	}

	public static boolean isDemiBoss(int npcId)
	{
		return TYPE_IDS.get(NylocasType.DEMI).contains(npcId);
	}

	public static boolean isBigNylo(int npcId)
	{
		return TYPE_IDS.get(NylocasType.BIG).contains(npcId);
	}

	public static boolean isSmallNylo(int npcId)
	{
		return TYPE_IDS.get(NylocasType.SMALL).contains(npcId);
	}

	public static boolean isWavesNylo(int npcId)
	{
		return isBigNylo(npcId) || isSmallNylo(npcId);
	}

	NylocasMap(int sm, int rg, int hm)
	{
		this(sm, rg, hm, -1, -1, -1);
	}

	NylocasMap(int hm)
	{
		this(-1, -1, hm);
	}
}