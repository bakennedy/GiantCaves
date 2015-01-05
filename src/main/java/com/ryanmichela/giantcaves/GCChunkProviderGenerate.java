package com.ryanmichela.giantcaves;

import net.minecraft.server.v1_8_R1.*;

import java.util.Random;

/**
 * Copyright 2013 Ryan Michela
 */
public class GCChunkProviderGenerate extends ChunkProviderGenerate {
    public GCChunkProviderGenerate(World world, long l, boolean b) {
        super(world, l, b, null);
    }

    @Override
    public Chunk getChunkAt(int i, int j) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Chunk getOrCreateChunk(int i, int j) {
        throw new UnsupportedOperationException();
    }

    public byte[][] getChunkSectionsAt(int ii, int jj) {
        Random i = ReflectionUtil.getProtectedValue(this, "i");
        World n = ReflectionUtil.getProtectedValue(this, "n");
        Boolean o = ReflectionUtil.getProtectedValue(this, "o");
        WorldGenBase t = ReflectionUtil.getProtectedValue(this, "t");
        WorldGenStronghold u = ReflectionUtil.getProtectedValue(this, "u");
        WorldGenVillage v = ReflectionUtil.getProtectedValue(this, "v");
        WorldGenMineshaft w = ReflectionUtil.getProtectedValue(this, "w");
        WorldGenLargeFeature x = ReflectionUtil.getProtectedValue(this, "x");
        WorldGenBase y = ReflectionUtil.getProtectedValue(this, "y");
        BiomeBase[] z = ReflectionUtil.getProtectedValue(this, "z");

        i.setSeed(ii * 341873128712L + jj * 132897987541L);

        Block[] arrayOfBlock = new Block[65536];
        byte[] arrayOfByte1 = new byte[65536];
        ChunkSnapshot snapshot = new ChunkSnapshot();
        for (int i = 0; i < arrayOfBlock.length; i++)
            snapshot.a(i, arrayOfBlock);
        a(ii, jj, arrayOfBlock);
        z = n.getWorldChunkManager().getBiomeBlock(z, ii * 16, jj * 16, 16, 16);
        a(ii, jj, arrayOfBlock, arrayOfByte1, z);

        t.a(this, n, ii, jj, arrayOfBlock);
        y.a(this, n, ii, jj, arrayOfBlock);
        if (o) {
            w.a(this, n, ii, jj, arrayOfBlock);
            v.a(this, n, ii, jj, arrayOfBlock);
            u.a(this, n, ii, jj, arrayOfBlock);
            x.a(this, n, ii, jj, arrayOfBlock);
        }

        Chunk localChunk = new Chunk(n, arrayOfBlock, arrayOfByte1, ii, jj);
        ChunkSection[] chunkSections = localChunk.getSections();

        byte[][] chunkSectionBytes = new byte[chunkSections.length][];
        for(int k = 0; k < chunkSectionBytes.length; k++) {
            if (chunkSections[k] != null) {
                chunkSectionBytes[k] = chunkSections[k].getIdArray();
            } else {
                chunkSectionBytes[k] = null;
            }
        }
        return chunkSectionBytes;
    }
}
