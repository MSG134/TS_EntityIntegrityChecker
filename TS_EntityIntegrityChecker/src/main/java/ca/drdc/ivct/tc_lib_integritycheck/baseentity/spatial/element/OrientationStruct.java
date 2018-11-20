/*******************************************************************************
 * Copyright (C) Her Majesty the Queen in Right of Canada, 
 * as represented by the Minister of National Defence, 2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ca.drdc.ivct.tc_lib_integritycheck.baseentity.spatial.element;

import java.util.StringTokenizer;

import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;

/**
 * The orientation of an object in the world coordinate system, as specified in
 * IEEE Std 1278.1-1995 section 1.3.2.
 */
public class OrientationStruct {

    // Threshold value for validation in radians
    private static final float THRESHOLD = (float) 0.0001; // 10^-4 rad or 0.0057 degrees

    /**
     * Rotation about the Z axis.(Radian)
     */
    private float psi;
    /**
     * Rotation about the Y axis.(Radian)
     */
    private float theta;
    /**
     * Rotation about the X axis.(Radian)
     */
    private float phi;

    /**
     *  the constructor wrap the angle between 0 and 2PI
     * @param psi Rotation about the Z axis in rad;
     * @param theta Rotation about the Y axis in rad;
     * @param phi Rotation about the X axis phi in rad;
     */
    public OrientationStruct(float psi, float theta, float phi) {
        this.setPsi(psi);
        this.setTheta(theta);
        this.setPhi(phi);
    }

    /**
     * Constructor from String with xyz separated by ";" 
     * @param orientationStr String semi-colon separated value
     */
    public OrientationStruct(String orientationStr) {
        StringTokenizer token = new StringTokenizer(orientationStr, ";");
        
        if (token.hasMoreTokens()) {
            this.setPsi(Float.parseFloat(token.nextToken()));
        }
        
        if (token.hasMoreTokens()) {
            this.setTheta(Float.parseFloat(token.nextToken()));
        }
        
        if (token.hasMoreTokens()) {
            this.setPhi(Float.parseFloat(token.nextToken()));
        }
    }

    public float getPsi() {
        return psi;
    }

    public void setPsi(float psi) {
        this.psi = (float) MathUtils.normalizeAngle(psi,  FastMath.PI);
    }

    public float getTheta() {
        return theta;
    }

    public void setTheta(float theta) {
        this.theta = (float) MathUtils.normalizeAngle(theta,  FastMath.PI);
    }

    public float getPhi() {
        return phi;
    }

    public void setPhi(float phi) {
        this.phi = (float) MathUtils.normalizeAngle(phi,  FastMath.PI);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(phi);
        result = prime * result + Float.floatToIntBits(psi);
        result = prime * result + Float.floatToIntBits(theta);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrientationStruct other = (OrientationStruct) obj;
        if (!MathUtils.equals(this.phi, other.phi, THRESHOLD))
            return false;
        if (!MathUtils.equals(this.psi, other.psi, THRESHOLD))
            return false;
        if (!MathUtils.equals(this.theta, other.theta, THRESHOLD))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrientationStruct [psi=" + psi + ", theta=" + theta + ", phi=" + phi + "]";
    }

}
