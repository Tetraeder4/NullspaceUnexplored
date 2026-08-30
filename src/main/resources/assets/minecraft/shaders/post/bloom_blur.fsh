#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BloomBlurConfig {
    vec2 BlurDir;
    float Radius;
    float SampleCount;
};

out vec4 fragColor;

void main() {
    vec2 texel = 1.0 / InSize;

    vec3 result = vec3(0.0);
    float weightSum = 0.0;

    const int MAX_SAMPLES = 16;

    for (int i = 0; i < MAX_SAMPLES; i++) {

        if (float(i) >= SampleCount)
        break;

        // Center the samples around the current pixel.
        float offset = float(i) - (SampleCount - 1.0) * 0.5;

        vec2 uv = texCoord
        + BlurDir * texel
        * offset
        * Radius;

        // Simple Gaussian-like weight.
        float sigma = max(SampleCount * 0.25, 0.001);
        float weight = exp(
                -(offset * offset) /
                (2.0 * sigma * sigma)
        );

        result += texture(InSampler, uv).rgb * weight;
        weightSum += weight;
    }

    result /= max(weightSum, 0.0001);

    fragColor = vec4(result, 1.0);
}